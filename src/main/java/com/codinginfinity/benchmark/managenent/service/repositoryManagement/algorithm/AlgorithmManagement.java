package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface AlgorithmManagement extends RepositoryEntityManagement<Algorithm>{
    @Override
    AddRepoEntityResponse<Algorithm> addRepoEntity(AddRepoEntityRequest<Algorithm> request);

    @Override
    DeleteRepoEntityResponse<Algorithm> deleteRepoEntity(DeleteRepoEntityRequest<Algorithm> request);

    @Override
    UpdateRepoEntityMetadataResponse<Algorithm> updateRepoEntityMetaData(UpdataRepoEntityMetadataRequest<Algorithm> request);

    @Override
    GetRepoEntityByIdResponse<Algorithm> getRepoEntityById(GetRepoEntityByIdRequest<Algorithm> request);

    @Override
    GetRepoEntityByUsernameResponse<Algorithm> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Algorithm> request);

    @Override
    GetRepoEntityByCategoryResponse<Algorithm> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<Algorithm> request);

    @Override
    GetUnusedRepoEntitysResponse<Algorithm> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Algorithm> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Algorithm> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Algorithm> request);
}
