package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetAllExperimentsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetExperimentByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.CreateExperimentResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.GetAllExperimentsResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.GetExperimentByIdResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;


/**
 * Defines the service contract for the experiment management modules, including all request, response and pre-conditions.
 * Important to note that all precoditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link ExperimentManegementImpl} class.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.request
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.respones
 *
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

public interface ExperimentManagement {

    /**
     * Pulls result objects off of a Queue and persists the results to a database.
     *
     * @param request The request encapsulated as an {@link SaveJobResultsRequest} object.
     * @return Returns the result in an encapsulated {@link SaveJobResultsResponse} object.
     * @since 1.0.0
     */
    SaveJobResultsResponse saveJobResults(SaveJobResultsRequest request);

    /**
     * Creates a new Experiment and all the Jobs that the experiment is composed of.
     * Then places all the Job objects on a Queue so that the jobs can be performed
     *
     * @param request The request encapsulated as an {@link CreateExperimentRequest} object.
     * @return Returns the result in an encapsulated {@link CreateExperimentResponse} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @throws NonExistentException Thrown when an invalid category is referenced in the request.
     * @since 1.0.0
     */
    CreateExperimentResponse createExperiment(CreateExperimentRequest request) throws NonExistentRepoEntityException, NonExistentException;

    /**
     * Returns aa experiment by the id of the experiment.
     *
     * @param request The request encapsulated as an {@link GetExperimentByIdRequest} object.
     * @return Returns the result in an encapsulated {@link GetExperimentByIdResponse} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @since 1.0.0
     */

    GetExperimentByIdResponse getExperimentById(GetExperimentByIdRequest request) throws NonExistentRepoEntityException;

    /**
     * Returns aa experiment by the id of the experiment.
     *
     * @param request The request encapsulated as an {@link GetExperimentByIdRequest} object.
     * @return Returns the result in an encapsulated {@link GetExperimentByIdResponse} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @since 1.0.0
     */

    GetAllExperimentsResponse getAllExperiments(GetAllExperimentsResponse request) throws NonExistentRepoEntityException;
}
