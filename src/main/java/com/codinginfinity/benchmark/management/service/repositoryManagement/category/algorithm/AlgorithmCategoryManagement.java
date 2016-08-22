package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;

/**
 * An interface extension of the {@link CategoryManagement} with generic
 * types of CategoryManagement<{@link AlgorithmCategory}>.
 *
 * A reference implementation is provided in {@link AlgorithmCategoryManagement}.
 *
 * Created by andrew on 2016/06/28.
 *
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.response
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.exception
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

public interface AlgorithmCategoryManagement extends CategoryManagement<AlgorithmCategory> {

    @Override
    AddCategoryResponse<AlgorithmCategory> addCategory(AddCategoryRequest<AlgorithmCategory> request) throws DuplicateCategoryException;

    @Override
    DeleteCategoryResponse<AlgorithmCategory> deleteCategory(DeleteCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException;

    @Override
    UpdateCategoryResponse<AlgorithmCategory> updateCategory(UpdateCategoryRequest<AlgorithmCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByIdResponse<AlgorithmCategory> getCategoryById(GetCategoryByIdRequest<AlgorithmCategory> request) throws NonExistentCategoryException;

    @Override
    GetCategoryByNameResponse<AlgorithmCategory> getCategoryByName(GetCategoryByNameRequest<AlgorithmCategory> request) throws NonExistentCategoryException;

    @Override
    GetAllCategoriesResponse<AlgorithmCategory> getAllCategories(GetAllCategoriesRequest<AlgorithmCategory> request)throws NonExistentCategoryException;
}
