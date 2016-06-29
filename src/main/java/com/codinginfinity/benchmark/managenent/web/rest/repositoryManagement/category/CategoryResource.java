package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.UpdateCategoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by andrew on 2016/06/29.
 */
@Slf4j
public abstract class CategoryResource<T extends Category, S extends CategoryRepository<T>, R extends CategoryManagement<T>> {

    public ResponseEntity<?> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException {
        getCategoryManagement().addCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException {
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

    protected abstract S getRepository();

    protected abstract R getCategoryManagement();
}
