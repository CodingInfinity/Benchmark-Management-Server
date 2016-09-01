package com.codinginfinity.benchmark.management.web.rest.experiementManagment;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.security.SecurityUtils;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.*;
import com.codinginfinity.benchmark.management.service.reporting.Reporting;
import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import com.codinginfinity.benchmark.management.service.reporting.request.DownloadResultsRequest;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @Inject
    private ExperimentManagement experimentManagement;

    @Inject
    private Reporting reporting;

    @Inject
    private UserManagement userManagement;

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
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/experiment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExperiment(@RequestBody CreateExperimentRequest request) throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.createExperiment(request), HttpStatus.OK);
    }

    /**
     * GET  /experiments  : Gets all experiment.
     * <p>
     * Gets all experiemnets
     * </p>
     *
     * @return the ResponseEntity with status 200 (Okay) and with body of all the experiments
     */
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/experiments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllExperiments() throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.getAllExperiments(new GetAllExperimentsRequest()), HttpStatus.OK);
    }

    /**
     * GET  /experiments/user  : Gets all experiment by the current user.
     * <p>
     * Gets all experiemnets by the current user
     * </p>
     *
     * @return the ResponseEntity with status 200 (Okay) and with body of all the experiments
     */
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/experiments/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCurrentUserExperiments() throws NonExistentException {
        User currentUser = userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser();
        return new ResponseEntity<>(experimentManagement.getAllUserExperiments(new GetAllUserExperimentsRequest(currentUser)), HttpStatus.OK);
    }

    /**
     * GET  /experiment/{id}  : Gets a experiment.
     * <p>
     * Gets a specified experiemnet by id, throws non existent if the experiment doesnt exist
     * </p>
     *
     * @param id Id of the experiment
     * @return the ResponseEntity with status 200 (Okay) and with body the new experiment id
     */
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/experiment/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExperimentById(@PathVariable Long id) throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.getExperimentById(new GetExperimentByIdRequest(id)), HttpStatus.OK);
    }

    /**
     * GET  /job/queue/{id}  : Gets boolean if a specified job is on the queue
     * <p>
     * Gets if a specified job is on the queue
     * </p>
     *
     * @param id Id of the job
     * @return the ResponseEntity with status 200 (Okay) and with body the boolean of the job is still on the queue
     */
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/job/onQueue/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isJobOnQueue(@PathVariable Long id) throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.isJobOnQueue(new IsJobOnQueueRequest(id)), HttpStatus.OK);
    }

    /**
     * GET  /experiments/report/weekly : Gets the weeks data of uploaded experiments
     * <p>
     * Gets the weeks data of uploaded experiments
     * </p>
     *
     * @return the ResponseEntity with status 200 (Okay) and with body the boolean of the job is still on the queue
     */
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/experiments/report/weekly",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getWeeklyExperimentReport(){
        return new ResponseEntity<>(experimentManagement.getExperimentWeeklyReport(new GetExperimentWeeklyReportRequest()), HttpStatus.OK);
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
    @Secured(AuthoritiesConstants.USER)
    @RequestMapping(value = "/job/{id}/results",
            method = RequestMethod.GET)
    public ResponseEntity<String> getCSVResults(@PathVariable Long id) throws ProcessingException, NonExistentException {

        String filename = "job-" + id + "-results.csv";

        String results = reporting.downloadResults(new DownloadResultsRequest(id)).getResults();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(com.codinginfinity.benchmark.management.http.MediaType.TEXT_CSV);
        responseHeaders.setContentLength(results.length());
        responseHeaders.setContentDispositionFormData("attachment", filename);

        return new ResponseEntity<>(results, responseHeaders, HttpStatus.OK);
    }
}

