package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.*;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;


/**
 * Defines the service contract for the experiment management modules, including all request, response and pre-conditions.
 * Important to note that all precoditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link ExperimentManegementImpl} class.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.request
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.response
 *
 * @author Reinhardt Cromhout
 * @author Fabio Loreggian
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
    GetAllExperimentsResponse getAllExperiments(GetAllExperimentsRequest request) throws NonExistentRepoEntityException;

    /**
     * Returns a boolean if the job is still on queue
     *
     * @param request The request encapsulated as an {@link IsJobOnQueueRequest} object.
     * @return Returns the result in an encapsulated {@link IsJobOnQueueResponse} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @since 1.0.0
     */
    IsJobOnQueueResponse isJobOnQueue(IsJobOnQueueRequest request) throws NonExistentRepoEntityException;

    /**
     * Returns all experiments by a specified user
     *
     * @param request The request encapsulated as an {@link GetAllUserExperimentsResponse} object.
     * @return Returns the result in an encapsulated {@link GetAllUserExperimentsRequest} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @since 1.0.0
     */
    GetAllUserExperimentsResponse getAllUserExperiments(GetAllUserExperimentsRequest request) throws NonExistentRepoEntityException;

    /**
     * Returns all experiments by a specified user
     *
     * @param request The request encapsulated as an {@link GetAllUserExperimentsResponse} object.
     * @return Returns the result in an encapsulated {@link GetAllUserExperimentsRequest} object.
     * @throws NonExistentRepoEntityException Thrown when an invalid repo entity is referenced
     * in the request.
     * @since 1.0.0
     */
    GetExperimentWeeklyReportResponse getExperimentWeeklyReport(GetExperimentWeeklyReportRequest request);

    /**
     * Register a node heartbeat with the management system's monitoring system.
     * @param request The request encapsulated as an {@link RegisterNodeHeartbeatRequest} object.
     * @return Returns the result in an encapsulated {@link RegisterNodeHeartbeatResponse} object.
     */
    RegisterNodeHeartbeatResponse registerNodeHeartbeat(RegisterNodeHeartbeatRequest request);

    /**
     * Remove a node from the management system's monitoring system.
     * @param request The request encapsulated as an {@link RemoveNodeRequest} object.
     * @return Returns the result in an encapsulated {@link RemoveNodeResponse} object.
     */
    RemoveNodeResponse removeNode(RemoveNodeRequest request) throws NonExistentException;

    /**
     * Get the status of a node by it's ID.
     * @param request The request encapsulated as an {@link GetNodeStatusByIdRequest} object.
     * @return Returns the result in an encapsulated {@link GetNodeStatusByIdResponse} object.
     */
    GetNodeStatusByIdResponse getNodeStatusById(GetNodeStatusByIdRequest request) throws NonExistentException;

    /**
     * Returns a summary of all nodes registered with the monitoring system.
     * @param request The request encapsulated as an {@link GetNodesSummaryRequest} object.
     * @return Returns the result in an encapsulated {@link GetNodesSummaryResponse} object.
     */
    GetNodesSummaryResponse getNodesSummary(GetNodesSummaryRequest request);
}
