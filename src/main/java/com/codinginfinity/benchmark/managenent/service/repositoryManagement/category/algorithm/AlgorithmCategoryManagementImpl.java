package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
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
import org.springframework.stereotype.Service;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class AlgorithmCategoryManagementImpl implements AlgorithmCategoryManagement {

    @Override
    public AddCategoryResponse<AlgorithmCategory> addCategory(AddCategoryRequest<AlgorithmCategory> request) throws DuplicateCategoryException {
        return null;
    }

    @Override
    public DeleteCategoryResponse<AlgorithmCategory> deleteCategory(DeleteCategoryRequest<AlgorithmCategory> request) throws NonExistentException {
        return null;
    }

    @Override
    public UpdateCategoryResponse<AlgorithmCategory> updateCategory(UpdateCategoryRequest<AlgorithmCategory> request) throws NonExistentException {
        return null;
    }

    @Override
    public GetCategoryResponse<AlgorithmCategory> getCategory(GetCategoryRequest<AlgorithmCategory> request) throws NonExistentException {
        return null;
    }
}
