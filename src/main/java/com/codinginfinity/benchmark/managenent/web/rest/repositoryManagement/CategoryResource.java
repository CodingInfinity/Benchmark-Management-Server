package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByNameRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.UpdateCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/29.
 */
@RestController
@RequestMapping("/api/repo/category")
@Slf4j
public abstract class CategoryResource<T extends Category, R extends CategoryRepository<T>, S extends CategoryManagement<T>> {

    public ResponseEntity<?> addAlgorithmCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException {
        getCategoryManagement().addCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<DeleteCategoryResponse<T>> deleteAlgorithmCategory(DeleteCategoryResponse<T> request) {
        return null;
    }

    public ResponseEntity<UpdateCategoryResponse<T>> updateCategoryResponseResponseEntity(UpdateCategoryRequest<T> request) {
        return null;
    }

    public ResponseEntity<GetCategoryByIdResponse<T>> getCategoryById(GetCategoryByIdRequest<T> request) {
        return null;
    }

    public ResponseEntity<GetCategoryByNameResponse<T>> getCategoryById(GetCategoryByNameRequest<T> request) {
        return null;
    }

    protected abstract T getNewCategory();

    protected abstract R getRepository();

    protected abstract S getCategoryManagement();

    protected abstract <E extends DuplicateCategoryException> E getDuplicateCategoryException();

    protected abstract <E extends NonExistentCategoryException> E getNonExistentCategoryException();
}
