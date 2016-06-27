package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by andrew on 2016/06/24.
 */
public interface DatasetManagement extends RepositoryEntityManagement<DatasetCategory, Dataset>{
    @Override
    AddRepoEntityResponse<Dataset> addRepoEntity(AddRepoEntityRequest<DatasetCategory, Dataset> request);

    @Override
    DeleteRepoEntityResponse<Dataset> deleteRepoEntity(DeleteRepoEntityRequest<Dataset> request);

    @Override
    UpdateRepoEntityMetadataResponse<Dataset> updateRepoEntityMetaData(UpdataRepoEntityMetadataRequest<Dataset> request);

    @Override
    GetRepoEntityByIdResponse<Dataset> getRepoEntityById(GetRepoEntityByIdRequest<Dataset> request);

    @Override
    GetRepoEntityByUsernameResponse<Dataset> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Dataset> request);

    @Override
    GetRepoEntityByCategoryResponse<Dataset> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<Dataset> request);

    @Override
    GetUnusedRepoEntitysResponse<Dataset> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Dataset> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Dataset> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Dataset> request);
}
