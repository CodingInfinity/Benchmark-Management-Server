package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.*;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.repository.MeasurementRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.*;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.*;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.QueueMessageUtils;
import com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByIdRequest;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesRequest;
import com.codinginfinity.benchmark.management.thrift.messages.JobSpecificationMessage;
import com.codinginfinity.benchmark.management.thrift.messages.LanguageType;
import com.codinginfinity.benchmark.management.thrift.messages.MeasurementType;
import com.codinginfinity.benchmark.management.thrift.messages.ResultMessage;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.apache.camel.Consume;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A reference implementation of the {@link ExperimentManagement} service contract.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.request
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.respones
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@Service
public class ExperimentManegementImpl implements ExperimentManagement {

    @Inject
    private UserManagement userManagement;

    @Inject
    private ExperimentRepository experimentRepository;

    @Inject
    private AlgorithmManagement algorithmManagement;

    @Inject
    private DatasetManagement datasetManagement;

    @Inject
    private QueueMessageUtils queueMessageUtils;

    @Inject
    private JobRepository jobRepository;

    @Inject
    private MeasurementRepository measurementRepository;

    @EndpointInject(uri="direct:jobs")
    private ProducerTemplate producerTemplate;

    @Consume(uri="direct:results")
    public void fetchResultsFromQueue(ResultMessage resultMessage){
        SaveJobResultsRequest saveJobResultsRequest = new SaveJobResultsRequest(resultMessage);
        saveJobResults(saveJobResultsRequest);
    }

    @Override
    public SaveJobResultsResponse saveJobResults(SaveJobResultsRequest request) {
        ResultMessage resultMessage = request.getResultMessage();
        Job job = jobRepository.findOne((long) resultMessage.getJobId());
        List<com.codinginfinity.benchmark.management.domain.Measurement> measurements = resultMessage.getMeasurements().stream().map(measurement -> {
            com.codinginfinity.benchmark.management.domain.Measurement transformedMeasurement = new com.codinginfinity.benchmark.management.domain.Measurement();
            transformedMeasurement.setJob(job);
            transformedMeasurement.setValue((double) measurement.getValue());
            return transformedMeasurement;
        }).collect(Collectors.toList());

        job.setMeasurements(measurements);
        jobRepository.save(job);
        return new SaveJobResultsResponse(job.getId(), job.getExperiment().getId());
    }

    /**
     * createExperiment creates the relevant jobs then puts the jobs onto the queue.
     * @param request
     * @return CreateExperimentResponse
     * @throws NonExistentRepoEntityException
     * @throws NonExistentException
     */
    @Override
    public CreateExperimentResponse createExperiment(CreateExperimentRequest request) throws NonExistentRepoEntityException, NonExistentException {
        Experiment experiment = new Experiment();
        Algorithm algorithm = algorithmManagement.getRepoEntityById(new GetRepoEntityByIdRequest<>(request.getAlgorithm())).getRepoEntity();
        List<Dataset> datasets = new ArrayList<>();
        Arrays.asList(request.getDatasets()).forEach(id ->{
            try {
                datasets.add(datasetManagement.getRepoEntityById(new GetRepoEntityByIdRequest<>(id)).getRepoEntity());
            } catch (NonExistentRepoEntityException e) {
                e.printStackTrace();
            }
        });

        experiment.setUser(userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser());
        experiment.getUser().getAuthorities().size();
        experiment.setProbeInterval(request.getProbeInterval());
        experiment.setTimeout(request.getTimeout());
        experiment.setLanguageType(LanguageType.findByValue(request.getLanguageType()));

        /*
        Iterates through the quantity of runs required, then through each measurement type then through each dataset
         */

        for(int i=0; i< request.getQuantity(); i++){
            for (int type:request.getMeasurementType()) {
                if(datasets.isEmpty()){
                    Job job = new Job();
                    job.setAlgorithm(algorithm);
                    job.setMeasurementType(MeasurementType.findByValue(type));
                    job.setExperiment(experiment);
                    experiment.getJobs().add(job);
                }else {
                    for (Dataset dataset : datasets) {
                        Job job = new Job();
                        job.setAlgorithm(algorithm);

                        job.setDataset(dataset);
                        job.setMeasurementType(MeasurementType.findByValue(type));
                        job.setExperiment(experiment);
                        experiment.getJobs().add(job);
                    }
                }
            }
        }
        experimentRepository.save(experiment);

        //Put the experiment(jobs) on the queue
        experiment.getJobs().forEach(job -> {
            JobSpecificationMessage jobSpecificationMessage = queueMessageUtils.convertJobToJobSpecificationMessage(job);
            producerTemplate.sendBody(jobSpecificationMessage);
        });

        return new CreateExperimentResponse(experiment.getId());
    }

    /**
     * getExperimentById returns an experiment
     * @param request
     * @return GetExperimentByIdResponse
     * @throws NonExistentRepoEntityException
     */
    @Override
    public GetExperimentByIdResponse getExperimentById(GetExperimentByIdRequest request) throws NonExistentRepoEntityException{
        Optional<Experiment> experiment = experimentRepository.findOneById(request.getId());

        if(!experiment.isPresent()){
            throw new NonExistentRepoEntityException("Experiment doesn't exist");
        }
        return new GetExperimentByIdResponse(experiment.get());
    }

    /**
     * getExperimentById returns an experiment
     * @param request
     * @return GetExperimentByIdResponse
     * @throws NonExistentRepoEntityException
     */
    @Override
    public GetAllExperimentsResponse getAllExperiments(GetAllExperimentsRequest request) throws NonExistentRepoEntityException{
        List<Experiment> experiments = experimentRepository.findAll();
        if(experiments.isEmpty()){
            throw new NonExistentRepoEntityException("There are no Experiments");
        }
        return new GetAllExperimentsResponse(experiments);
    }

    /**
     * isJobOnQueue returns a boolean if the job is still on the queue
     * @param request
     * @return IsJobOnQueueResponse
     * @throws NonExistentRepoEntityException
     */
    @Override
    public IsJobOnQueueResponse isJobOnQueue(IsJobOnQueueRequest request) throws NonExistentRepoEntityException{
        boolean onQueue;

        Optional<Job> job = jobRepository.findOneById(request.getId());
        if(!job.isPresent()){
            throw new NonExistentRepoEntityException("The job does not exist");
        }
        List<Measurement> measurements = measurementRepository.findAll();
        if(!measurements.isEmpty()){
            onQueue = true;
            for (Measurement measurement: measurements) {
                if(measurement.getJob().getId() == job.get().getId()){
                    onQueue = false;
                }
            }
        }else{
            onQueue = true;
        }
        return new IsJobOnQueueResponse(onQueue);
    }


    /**
     * getAllUserExperiments returns all experiments by a user
     * @param request
     * @return GetAllUserExperimentsResponse
     * @throws NonExistentRepoEntityException
     */
    @Override
    public GetAllUserExperimentsResponse getAllUserExperiments(GetAllUserExperimentsRequest request) throws NonExistentRepoEntityException{
        List<Experiment> experiments = experimentRepository.findAllByUser(request.getUser());

        if(experiments.isEmpty()){
            throw new NonExistentRepoEntityException("There are no experiments uploaded for this user");
        }
        return new GetAllUserExperimentsResponse(experiments);

    }

    /**
     * getExperimentWeeklyReport returns all experiments by a user
     * @param request
     * @return GetExperimentWeeklyReportResponse
     */
    @Override
    public GetExperimentWeeklyReportResponse getExperimentWeeklyReport(GetExperimentWeeklyReportRequest request){
        ZonedDateTime today = ZonedDateTime.now();

        List<Integer> totalExperiments = new ArrayList<>();
        List<Integer> jobsCompleted = new ArrayList<>();
        List<Integer> totalWallTime = new ArrayList<>();
        List<Integer> totalCPU = new ArrayList<>();
        List<Integer> totalMemory = new ArrayList<>();

        List<Experiment> experiments = experimentRepository.findAll();
        List<Job> jobs = jobRepository.findAll();


        for (int i = 0; i < 7; i++) {
            totalExperiments.add(0);
            totalMemory.add(0);
            jobsCompleted.add(0);
            totalWallTime.add(0);
            totalCPU.add(0);
        }
        if(!experiments.isEmpty() && !jobs.isEmpty()){
            for(Experiment experiment: experiments){
                int weekdayDiff = (today.getDayOfYear() - experiment.getRequestedDate().getDayOfYear());
                if(weekdayDiff  < 7){
                    totalExperiments.set(weekdayDiff, totalExperiments.get(weekdayDiff)+1);
                }
            }

            for(Job job: jobs){
                int weekdayDiff = (today.getDayOfYear() - job.getExperiment().getRequestedDate().getDayOfYear());
                if(weekdayDiff  < 7) {
                    switch (job.getMeasurementType()) {
                        case CPU:
                            totalCPU.set(weekdayDiff, totalCPU.get(weekdayDiff) + 1);
                            break;
                        case MEM:
                            totalMemory.set(weekdayDiff, totalMemory.get(weekdayDiff) + 1);
                            break;
                        case TIME:
                            totalWallTime.set(weekdayDiff, totalWallTime.get(weekdayDiff) + 1);
                            break;

                    }
                    if (!job.getMeasurements().isEmpty()) {
                        jobsCompleted.set(weekdayDiff, jobsCompleted.get(weekdayDiff) + 1);
                    }
                }
            }
        }

        GetExperimentWeeklyReportResponse response = new GetExperimentWeeklyReportResponse(today.getDayOfWeek(), totalExperiments, jobsCompleted, totalWallTime, totalCPU, totalMemory);
        return response;
    }
}
