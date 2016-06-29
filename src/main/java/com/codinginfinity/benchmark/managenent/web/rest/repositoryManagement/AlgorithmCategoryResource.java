package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.UpdateCategoryRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/29.
 */
@RestController
@RequestMapping("/api/repo/category")
public class AlgorithmCategoryResource extends CategoryResource<AlgorithmCategory, AlgorithmCategoryRepository, AlgorithmCategoryManagement> {

    @Inject
    private AlgorithmCategoryRepository repository;

    @Inject
    private AlgorithmCategoryManagement categoryManagement;

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(AddCategoryRequest<AlgorithmCategory> request) throws DuplicateCategoryException {
        return super.addCategory(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> deleteCategory(DeleteCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException {
        return super.deleteCategory(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> updateCategory(UpdateCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException {
        return super.updateCategory(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<AlgorithmCategory> getCategoryById(GetCategoryByIdRequest<AlgorithmCategory> request) throws NonExistentCategoryException {
        return super.getCategoryById(request);
    }

    @Override
    protected AlgorithmCategory getNewCategory() {
        return new AlgorithmCategory();
    }

    @Override
    protected AlgorithmCategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    protected AlgorithmCategoryManagement getCategoryManagement() {
        return this.categoryManagement;
    }
}
