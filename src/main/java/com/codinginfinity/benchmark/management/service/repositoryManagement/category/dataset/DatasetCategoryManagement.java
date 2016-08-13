package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetAllCategoriesRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.GetAllCategoriesResponse;

/**
 * Created by andrew on 2016/06/25.
 */
public interface DatasetCategoryManagement extends CategoryManagement<DatasetCategory> {

    @Override
    AddCategoryResponse<DatasetCategory> addCategory(AddCategoryRequest<DatasetCategory> request) throws DuplicateCategoryException;

    @Override
    DeleteCategoryResponse<DatasetCategory> deleteCategory(DeleteCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    UpdateCategoryResponse<DatasetCategory> updateCategory(UpdateCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByIdResponse<DatasetCategory> getCategoryById(GetCategoryByIdRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByNameResponse<DatasetCategory> getCategoryByName(GetCategoryByNameRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetAllCategoriesResponse<DatasetCategory> getAllCategories(GetAllCategoriesRequest<DatasetCategory> request) throws  NonExistentCategoryException;
}
