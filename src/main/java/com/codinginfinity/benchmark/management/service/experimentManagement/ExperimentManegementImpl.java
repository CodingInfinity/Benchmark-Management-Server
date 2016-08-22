package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.CreateExperimentResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsResponse;
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
import org.apache.camel.Consume;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    JobRepository jobRepository;

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

            Instant i = Instant.ofEpochSecond(measurement.getTimestamp());
            ZonedDateTime z = ZonedDateTime.ofInstant( i, ZoneId.of("Africa/Johannesburg"));
            transformedMeasurement.setTimestamp(z);
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
                for (Dataset dataset: datasets) {
                    Job job = new Job();
                    job.setAlgorithm(algorithm);
                    job.setDataset(dataset);
                    job.setMeasurementType(MeasurementType.findByValue(type));
                    job.setExperiment(experiment);
                    experiment.getJobs().add(job);
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
}
