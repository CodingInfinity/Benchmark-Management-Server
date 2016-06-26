package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
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
public class DatasetCategoryManagementImpl implements DatasetCategoryManagement {

    @Override
    public AddCategoryResponse<DatasetCategory> addCategory(AddCategoryRequest<DatasetCategory> request) {
        return null;
    }

    @Override
    public DeleteCategoryResponse<DatasetCategory> deleteCategory(DeleteCategoryRequest<DatasetCategory> request) {
        return null;
    }

    @Override
    public UpdateCategoryResponse<DatasetCategory> updateCategory(UpdateCategoryRequest<DatasetCategory> request) {
        return null;
    }

    @Override
    public GetCategoryResponse<DatasetCategory> getCategory(GetCategoryRequest<DatasetCategory> request) {
        return null;
    }
}
