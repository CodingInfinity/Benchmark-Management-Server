package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
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
 * Created by andrew on 2016/06/26.
 */
public interface CategoryManagement<T extends Category> {

    AddCategoryResponse<T> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException;
    DeleteCategoryResponse<T> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentException;
    UpdateCategoryResponse<T> updateCategory(UpdateCategoryRequest<T> request) throws NonExistentException;
    GetCategoryResponse<T> getCategory(GetCategoryRequest<T> request) throws NonExistentException;
}
