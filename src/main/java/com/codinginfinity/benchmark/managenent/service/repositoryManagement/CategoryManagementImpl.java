package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.UpdateCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.AddCategoryResponse;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.DeleteCategoryResponse;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.GetCategoryResponse;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.UpdateCategoryResponse;

/**
 * Created by andrew on 2016/06/27.
 */
public class CategoryManagementImpl<T extends Category> implements CategoryManagement {

    @Override
    public AddCategoryResponse addCategory(AddCategoryRequest request) throws DuplicateCategoryException {
        return null;
    }

    @Override
    public DeleteCategoryResponse deleteCategory(DeleteCategoryRequest request) throws NonExistentException {
        return null;
    }

    @Override
    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest request) throws NonExistentException {
        return null;
    }

    @Override
    public GetCategoryResponse getCategory(GetCategoryRequest request) throws NonExistentException {
        return null;
    }
}
