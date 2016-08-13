package com.codinginfinity.benchmark.management.web.rest.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.UpdateCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetAllCategoriesRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

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
    @Override
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

    @RequestMapping(value = "/algorithm/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<AlgorithmCategory> getCategoryById(@PathVariable(value = "id") Long id) throws NonExistentCategoryException {
        return super.getCategoryById(id);
    }

    @RequestMapping(value = "/algorithm/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<AlgorithmCategory>> getAllCategories(GetAllCategoriesRequest<AlgorithmCategory> request)throws NonExistentCategoryException{
        return super.getAllCategories(request);
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
