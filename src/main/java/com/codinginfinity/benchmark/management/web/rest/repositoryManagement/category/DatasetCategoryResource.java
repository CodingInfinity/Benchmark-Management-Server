package com.codinginfinity.benchmark.management.web.rest.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
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
 * categories of dataset repository entities.
 *
 * @see com.codinginfinity.benchmark.management.domain.DatasetCategory
 * @see com.codinginfinity.benchmark.management.repository.DatasetCategoryRepository
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.DatasetCategoryManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api/repo/category")
public class DatasetCategoryResource extends CategoryResource<DatasetCategory, DatasetCategoryRepository, DatasetCategoryManagement> {

    @Inject
    private DatasetCategoryRepository repository;

    @Inject
    private DatasetCategoryManagement categoryManagement;

    @RequestMapping(value = "/dataset",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest<DatasetCategory> request) throws DuplicateCategoryException {
        return super.addCategory(request);
    }

    @RequestMapping(value = "/dataset",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> deleteCategory(@RequestBody DeleteCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException {
        return super.deleteCategory(request);
    }

    @RequestMapping(value = "/dataset",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException {
        return super.updateCategory(request);
    }

    @RequestMapping(value = "/dataset/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<DatasetCategory> getCategoryById(@PathVariable(value = "id") Long id) throws NonExistentCategoryException {
        return super.getCategoryById(id);
    }

    @RequestMapping(value = "/dataset/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<DatasetCategory>> getAllCategories(GetAllCategoriesRequest<DatasetCategory> request)throws NonExistentCategoryException{
        return super.getAllCategories(request);
    }

    @Override
    protected DatasetCategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    protected DatasetCategoryManagement getCategoryManagement() {
        return this.categoryManagement;
    }
}
