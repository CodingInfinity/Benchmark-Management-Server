package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.LinkedException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;

/**
 * An interface extension of the {@link CategoryManagement} with generic
 * types of CategoryManagement<{@link DatasetCategory}>.
 *
 * A reference implementation is provided in {@link DatasetCategoryManagementImpl}.
 *
 * Created by andrew on 2016/06/25.
 *
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.response
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.exception
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

public interface DatasetCategoryManagement extends CategoryManagement<DatasetCategory> {

    @Override
    AddCategoryResponse<DatasetCategory> addCategory(AddCategoryRequest<DatasetCategory> request) throws DuplicateCategoryException;

    @Override
    DeleteCategoryResponse<DatasetCategory> deleteCategory(DeleteCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException, LinkedException;

    @Override
    UpdateCategoryResponse<DatasetCategory> updateCategory(UpdateCategoryRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByIdResponse<DatasetCategory> getCategoryById(GetCategoryByIdRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByNameResponse<DatasetCategory> getCategoryByName(GetCategoryByNameRequest<DatasetCategory> request) throws NonExistentCategoryException;

    @Override
    GetAllCategoriesResponse<DatasetCategory> getAllCategories(GetAllCategoriesRequest<DatasetCategory> request) throws NonExistentCategoryException;
}
