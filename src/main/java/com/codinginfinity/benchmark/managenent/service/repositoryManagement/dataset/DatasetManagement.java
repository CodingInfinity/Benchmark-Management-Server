package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by andrew on 2016/06/24.
 */
public interface DatasetManagement extends RepositoryEntityManagement<DatasetCategory, Dataset>{
    @Override
    AddRepoEntityResponse<Dataset> addRepoEntity(AddRepoEntityRequest<DatasetCategory, Dataset> request) throws NonExistentException;

    @Override
    DeleteRepoEntityResponse<Dataset> deleteRepoEntity(DeleteRepoEntityRequest<Dataset> request) throws NonExistentRepoEntityException;

    @Override
    UpdateRepoEntityMetadataResponse<Dataset> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<DatasetCategory, Dataset> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByIdResponse<Dataset> getRepoEntityById(GetRepoEntityByIdRequest<Dataset> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByUsernameResponse<Dataset> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Dataset> request);

    @Override
    GetRepoEntityByCategoryResponse<Dataset> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<DatasetCategory, Dataset> request);

    @Override
    GetUnusedRepoEntitysResponse<Dataset> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Dataset> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Dataset> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Dataset> request);
}
