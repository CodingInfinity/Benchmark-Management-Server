package com.codinginfinity.benchmark.management.web.rest.experiementManagment;

import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.reporting.Reporting;
import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import com.codinginfinity.benchmark.management.service.reporting.request.DownloadResultsRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Defines RESTful API endpoints for all management related to experiment creation and management.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api")
public class ExperimentResource {

    private final MediaType TEXT_CSV = new MediaType("text", "csv");

    @Inject
    private ExperimentManagement experimentManagement;

    @Inject
    private Reporting reporting;

    @Inject
    private JobRepository jobRepository;

    /**
     * POST  /experiment  : Creates a new experiment.
     * <p>
     * Creates a new experiment based on the requirements selected by the user.
     * Throws a non existent exception if a algorithm or a dataset doesnt exist
     * </p>
     *
     * @param request the HTTP request with the json representation of the parameters to be used to create the experiment
     * @return the ResponseEntity with status 200 (Okay) and with body the new experiment id
     */
    @RequestMapping(value = "/experiment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExperiment(@RequestBody CreateExperimentRequest request) throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.createExperiment(request), HttpStatus.OK);
    }

    /**
     * GET  /job/{id}/results  : Get the results for job with {id}.
     * <p>
     * Returns the results for the job in a CSV format to be download by the user.
     * Throws a {@link ProcessingException} if conversion could not be done successfully.
     * </p>
     *
     * @param id The id of the job whose results we want to obtain
     * @return ResponseEntity with status 200 (OK), with a String body containing the results in CSV format
     */
    @RequestMapping(value = "/job/{id}/results",
            method = RequestMethod.GET)
    public ResponseEntity<String> getCSVResults(@PathVariable Long id) throws ProcessingException {

        StringBuilder filename = new StringBuilder()
                                    .append("job-")
                                    .append(id)
                                    .append("-results.csv");

        String results = reporting.downloadResults(new DownloadResultsRequest(id)).getResults();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(com.codinginfinity.benchmark.management.http.MediaType.TEXT_CSV);
        responseHeaders.setContentLength(results.length());
        responseHeaders.setContentDispositionFormData("attachment", filename.toString());

        return new ResponseEntity<>(results, responseHeaders, HttpStatus.OK);
    }
}

