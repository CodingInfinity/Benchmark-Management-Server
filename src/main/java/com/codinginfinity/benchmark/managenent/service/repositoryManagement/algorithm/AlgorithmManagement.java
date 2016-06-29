package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface AlgorithmManagement extends RepositoryEntityManagement<AlgorithmCategory, Algorithm>{
    @Override
    AddRepoEntityResponse<Algorithm> addRepoEntity(AddRepoEntityRequest<AlgorithmCategory, Algorithm> request);

    @Override
    DeleteRepoEntityResponse<Algorithm> deleteRepoEntity(DeleteRepoEntityRequest<Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    UpdateRepoEntityMetadataResponse<Algorithm> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<AlgorithmCategory, Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByIdResponse<Algorithm> getRepoEntityById(GetRepoEntityByIdRequest<Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByUsernameResponse<Algorithm> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Algorithm> request);

    @Override
    GetRepoEntityByCategoryResponse<Algorithm> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<AlgorithmCategory, Algorithm> request);

    @Override
    GetUnusedRepoEntitysResponse<Algorithm> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Algorithm> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Algorithm> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Algorithm> request);
}
