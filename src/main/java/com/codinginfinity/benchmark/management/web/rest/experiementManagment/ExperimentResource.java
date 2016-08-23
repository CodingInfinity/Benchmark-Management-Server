package com.codinginfinity.benchmark.management.web.rest.experiementManagment;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;

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
}

