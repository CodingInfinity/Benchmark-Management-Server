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

/**
 * Created by fabio on 2016/08/14.
 */
@RestController
@RequestMapping("/api")
public class ExperimentResource {
    @Inject
    private ExperimentManagement experimentManagement;

    @RequestMapping(value = "/experiment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExperiment(@RequestBody CreateExperimentRequest request) throws NonExistentException {
        return new ResponseEntity<>(experimentManagement.createExperiment(request), HttpStatus.OK);
    }
}

