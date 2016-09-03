package com.codinginfinity.benchmark.management.web.rest.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetAllCategoriesRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.UpdateCategoryRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Defines abstract RESTful API endpoints for all management related to
 * categories of algorithm repository entities.
 *
 * @see com.codinginfinity.benchmark.management.domain.AlgorithmCategory
 * @see com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api/repo/category")
public class AlgorithmCategoryResource extends CategoryResource<AlgorithmCategory, AlgorithmCategoryRepository, AlgorithmCategoryManagement> {

    @Inject
    private AlgorithmCategoryRepository repository;

    @Inject
    private AlgorithmCategoryManagement categoryManagement;

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest<AlgorithmCategory> request) throws DuplicateCategoryException {
        return super.addCategory(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> deleteCategory(@RequestBody DeleteCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException {
        return super.deleteCategory(request);
    }

    @RequestMapping(value = "/algorithm",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException {
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
