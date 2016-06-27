package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import org.springframework.stereotype.Service;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class AlgorithmManagementImpl implements AlgorithmManagement {
    @Override
    public AddRepoEntityResponse<Algorithm> addRepoEntity(AddRepoEntityRequest<Algorithm> request) {
        return null;
    }

    @Override
    public DeleteRepoEntityResponse<Algorithm> deleteRepoEntity(DeleteRepoEntityRequest<Algorithm> request) {
        return null;
    }

    @Override
    public UpdateRepoEntityMetadataResponse<Algorithm> updateRepoEntityMetaData(UpdataRepoEntityMetadataRequest<Algorithm> request) {
        return null;
    }

    @Override
    public GetRepoEntityByIdResponse<Algorithm> getRepoEntityById(GetRepoEntityByIdRequest<Algorithm> request) {
        return null;
    }

    @Override
    public GetRepoEntityByUsernameResponse<Algorithm> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Algorithm> request) {
        return null;
    }

    @Override
    public GetRepoEntityByCategoryResponse<Algorithm> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<Algorithm> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntitysResponse<Algorithm> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Algorithm> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntityByUsernameResponse<Algorithm> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Algorithm> request) {
        return null;
    }
}
