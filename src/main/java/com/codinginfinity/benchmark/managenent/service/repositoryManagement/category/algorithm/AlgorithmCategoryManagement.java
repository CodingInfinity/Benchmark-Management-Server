package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
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
public interface AlgorithmCategoryManagement extends CategoryManagement<AlgorithmCategory> {

    @Override
    AddCategoryResponse<AlgorithmCategory> addCategory(AddCategoryRequest<AlgorithmCategory> request) throws DuplicateCategoryException;

    @Override
    DeleteCategoryResponse<AlgorithmCategory> deleteCategory(DeleteCategoryRequest<AlgorithmCategory> request) throws NonExistentException;

    @Override
    UpdateCategoryResponse<AlgorithmCategory> updateCategory(UpdateCategoryRequest<AlgorithmCategory> request) throws NonExistentException;

    @Override
    GetCategoryResponse<AlgorithmCategory> getCategory(GetCategoryRequest<AlgorithmCategory> request) throws NonExistentException;
}
