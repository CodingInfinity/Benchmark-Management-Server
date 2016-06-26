package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
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
 * Created by andrew on 2016/06/25.
 */
public interface DatasetCategoryManagement extends CategoryManagement<DatasetCategory> {

    @Override
    AddCategoryResponse<DatasetCategory> addCategory(AddCategoryRequest<DatasetCategory> request) throws DuplicateCategoryException;

    @Override
    DeleteCategoryResponse<DatasetCategory> deleteCategory(DeleteCategoryRequest<DatasetCategory> request) throws NonExistentException;

    @Override
    UpdateCategoryResponse<DatasetCategory> updateCategory(UpdateCategoryRequest<DatasetCategory> request) throws NonExistentException;

    @Override
    GetCategoryResponse<DatasetCategory> getCategory(GetCategoryRequest<DatasetCategory> request) throws NonExistentException;
}
