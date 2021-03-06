package com.codinginfinity.benchmark.management.service.experimentManagement.utils;

import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.elasticsearch.FileRepository;
import com.codinginfinity.benchmark.management.thrift.messages.JobSpecificationMessage;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by fabio on 2016/08/14.
 */




@Service
public class QueueMessageUtils {

    @Inject
    private FileRepository fileRepository;

    /**
     * Transforms the job into a representation defined y thrift
     * @param job
     * @return JobSpecificationMessage is the Thrift representation of a job.
     */
    public JobSpecificationMessage convertJobToJobSpecificationMessage(Job job){
        JobSpecificationMessage jobSpecificationMessage = new JobSpecificationMessage();
        jobSpecificationMessage.setMeasurementType(job.getMeasurementType());

        jobSpecificationMessage.setAlgorithm(fileRepository.findOne(getRepoEntityPrefix(job.getAlgorithm()) +  job.getAlgorithm().getFilename()).getContents());
        if(job.getDataset()==null){
            byte[] buffer = null;
            jobSpecificationMessage.setDataset(buffer);
        }else {
            jobSpecificationMessage.setDataset(fileRepository.findOne(getRepoEntityPrefix(job.getDataset()) + job.getDataset().getFilename()).getContents());
        }
        //TODO Thrift generated classes with Long error
        jobSpecificationMessage.setExperimentId(job.getExperiment().getId().intValue());
        jobSpecificationMessage.setJobId(job.getId().intValue());
        jobSpecificationMessage.setLanguageType(job.getExperiment().getLanguageType());
        jobSpecificationMessage.setProbeInterval(job.getExperiment().getProbeInterval());
        jobSpecificationMessage.setTimeout(job.getExperiment().getTimeout());
        return jobSpecificationMessage;
    }

    /**
     * Returns the prefeix for the repoEntity stored in elastic search
     * @param repoEntity
     * @return A prefix representation of the file
     */
    public String getRepoEntityPrefix(RepoEntity repoEntity){
        StringBuilder prefix = new StringBuilder();
        prefix.append(repoEntity.getClass().getSimpleName().subSequence(0,3));
        prefix.append("-");
        prefix.append(repoEntity.getId());
        prefix.append("_");
        return prefix.toString();
    }
}
