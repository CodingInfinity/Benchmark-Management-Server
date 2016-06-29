package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by andrew on 2016/06/29.
 */
@Slf4j
public abstract class CategoryResource<T extends Category, R extends CategoryRepository<T>, S extends CategoryManagement<T>> {

    public ResponseEntity<?> addAlgorithmCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException {
        getCategoryManagement().addCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAlgorithmCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException {
        getCategoryManagement().deleteCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateCategoryResponseResponseEntity(UpdateCategoryRequest<T> request)
            throws NonExistentCategoryException {
        getCategoryManagement().updateCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<T> getCategoryById(GetCategoryByIdRequest<T> request) throws NonExistentCategoryException {
        T category = getCategoryManagement().getCategoryById(request).getCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    public ResponseEntity<T> getCategoryById(GetCategoryByNameRequest<T> request)
            throws NonExistentCategoryException {
        T category = getCategoryManagement().getCategoryByName(request).getCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    protected abstract T getNewCategory();

    protected abstract R getRepository();

    protected abstract S getCategoryManagement();

    protected abstract <E extends DuplicateCategoryException> E getDuplicateCategoryException();

    protected abstract <E extends NonExistentCategoryException> E getNonExistentCategoryException();
}