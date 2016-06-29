package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/06/29.
 */
@RestController
@RequestMapping("/api/repo")
public class AlgorithmResource extends RepositoryEntityResource<AlgorithmCategory, Algorithm, AlgorithmRepository, AlgorithmManagement>{

    @Inject
    private AlgorithmRepository repository;

    @Inject
    private AlgorithmManagement algorithmManagement;

    @Override
    protected AlgorithmRepository getRepository() {
        return this.repository;
    }

    @Override
    protected AlgorithmManagement getRepositoryEntityManagement() {
        return this.algorithmManagement;
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> addRepoEntity(AddRepoEntityRequest<AlgorithmCategory, Algorithm> request) {
        return super.addRepoEntity(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> deleteRepoEntity(DeleteRepoEntityRequest<Algorithm> request) throws NonExistentRepoEntityException {
        return super.deleteRepoEntity(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<AlgorithmCategory, Algorithm> request) throws NonExistentRepoEntityException {
        return super.updateRepoEntityMetaData(request);
    }

    @RequestMapping(value = "/algorithm/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityById(@PathVariable(value = "id") Long id) throws NonExistentRepoEntityException {
        return super.getRepoEntityById(id);
    }

    @RequestMapping(value = "/algorithm/{userName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityByUsername(@PathVariable(value = "userName") String userName) {
        return super.getRepoEntityByUsername(userName);
    }
}
