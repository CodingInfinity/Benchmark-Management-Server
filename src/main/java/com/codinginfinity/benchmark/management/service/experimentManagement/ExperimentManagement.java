package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.CreateExperimentResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;

/*
 * Created by reinhardt on 2016/08/09.
 */

/**
 * Defines the service contract for the experiment management modules, including all request, response and pre-conditions.
 * Important to note that all precoditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link ExperimentManegementImpl} class.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.exception
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.request
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.respones
 *
 * @author Reinhardt Cromhout
 *
 * @version 1.0.0
 */

public interface ExperimentManagement {

    SaveJobResultsResponse saveJobResults(SaveJobResultsRequest request);

    CreateExperimentResponse createExperiment(CreateExperimentRequest request) throws NonExistentRepoEntityException, NonExistentException;
}
