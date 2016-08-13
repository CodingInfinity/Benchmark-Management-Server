package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetAllCategoriesRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.GetAllCategoriesResponse;

/**
 * Created by andrew on 2016/06/28.
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
