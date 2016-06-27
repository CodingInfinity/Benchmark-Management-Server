package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import org.springframework.stereotype.Service;

/**
 * Created by andrew on 2016/06/24.
 */
@Service
public class DatasetManagementImpl implements DatasetManagement {
    @Override
    public AddRepoEntityResponse<Dataset> addRepoEntity(AddRepoEntityRequest<Dataset> request) {
        return null;
    }

    @Override
    public DeleteRepoEntityResponse<Dataset> deleteRepoEntity(DeleteRepoEntityRequest<Dataset> request) {
        return null;
    }

    @Override
    public UpdateRepoEntityMetadataResponse<Dataset> updateRepoEntityMetaData(UpdataRepoEntityMetadataRequest<Dataset> request) {
        return null;
    }

    @Override
    public GetRepoEntityByIdResponse<Dataset> getRepoEntityById(GetRepoEntityByIdRequest<Dataset> request) {
        return null;
    }

    @Override
    public GetRepoEntityByUsernameResponse<Dataset> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Dataset> request) {
        return null;
    }

    @Override
    public GetRepoEntityByCategoryResponse<Dataset> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<Dataset> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntitysResponse<Dataset> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<Dataset> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntityByUsernameResponse<Dataset> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Dataset> request) {
        return null;
    }
}
