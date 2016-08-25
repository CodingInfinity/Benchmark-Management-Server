package com.codinginfinity.benchmark.management.service.reporting;

import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import com.codinginfinity.benchmark.management.service.reporting.request.DownloadResultsRequest;
import com.codinginfinity.benchmark.management.service.reporting.response.DownloadResultsResponse;

/**
 * Defines the service contract for the reporting module, including all request, response and pre-conditions.
 * Important to note that all pre-conditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link ReportingImpl} class.
 *
 * @see com.codinginfinity.benchmark.management.service.reporting.exception
 * @see com.codinginfinity.benchmark.management.service.reporting.request
 * @see com.codinginfinity.benchmark.management.service.reporting.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
public interface Reporting {

    /**
     * Used to retrieve the results in to present them to the user for download.
     * @param request The request encapsulated as an {@link DownloadResultsRequest} object.
     * @return Returns the result in an encapsulated {@link DownloadResultsResponse} object.
     * @throws ProcessingException Thrown when the system could not convert results into a presentable format.
     * @since 1.0.0
     */
    DownloadResultsResponse downloadResults(DownloadResultsRequest request) throws ProcessingException;
}
