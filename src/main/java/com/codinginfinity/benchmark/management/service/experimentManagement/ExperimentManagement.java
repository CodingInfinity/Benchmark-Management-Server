package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.CreateExperimentResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;

/**
 * Created by reinhardt on 2016/08/09.
 */
public interface ExperimentManagement {

    SaveJobResultsResponse saveJobResults(SaveJobResultsRequest request);

    CreateExperimentResponse createExperiment(CreateExperimentRequest request) throws NonExistentRepoEntityException, NonExistentException;
}