package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by reinhardt on 2016/06/27.
 */
public interface RepositoryEntityManagement<C extends Category, T extends RepoEntity<C>> {
    AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request);
    DeleteRepoEntityResponse<T> deleteRepoEntity(DeleteRepoEntityRequest<T> request);
    UpdateRepoEntityMetadataResponse<T> updateRepoEntityMetaData(UpdataRepoEntityMetadataRequest<T> request);
    GetRepoEntityByIdResponse<T> getRepoEntityById(GetRepoEntityByIdRequest<T> request);
    GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request);
    GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request);
    GetUnusedRepoEntitysResponse<T> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<T> request);
    GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request);
}
