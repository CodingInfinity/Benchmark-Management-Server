package com.codinginfinity.benchmark.management.web.rest.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.LinkedException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Defines abstract RESTful API endpoints for all management related to
 * categories of repository entities.
 *
 * @see com.codinginfinity.benchmark.management.domain.Category
 * @see com.codinginfinity.benchmark.management.repository.CategoryRepository
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@Slf4j
public abstract class CategoryResource<T extends Category, S extends CategoryRepository<T>, R extends CategoryManagement<T>> {

    public ResponseEntity<?> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException {
        getCategoryManagement().addCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException, LinkedException {
        getCategoryManagement().deleteCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateCategory(UpdateCategoryRequest<T> request)
            throws NonExistentCategoryException {
        getCategoryManagement().updateCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<T> getCategoryById(Long id) throws NonExistentCategoryException {
        T category = getCategoryManagement().getCategoryById(new GetCategoryByIdRequest<T>(id)).getCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    public ResponseEntity<List<T>> getAllCategories(GetAllCategoriesRequest<T> request)throws NonExistentCategoryException{
        List<T> categories = getCategoryManagement().getAllCategories(request).getCategories();
        return new ResponseEntity<List<T>>(categories, HttpStatus.OK);
    }

    protected abstract S getRepository();

    protected abstract R getCategoryManagement();
}
