package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.managenent.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.managenent.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

/**
 * Created by reinhardt on 2016/06/27.
 */
public interface RepositoryEntityManagement<C extends Category, T extends RepoEntity<C>> {
    AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException;
    DeleteRepoEntityResponse<T> deleteRepoEntity(DeleteRepoEntityRequest<T> request) throws NonExistentRepoEntityException;
    UpdateRepoEntityMetadataResponse<T> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<C,T> request) throws NonExistentRepoEntityException;
    GetRepoEntityByIdResponse<T> getRepoEntityById(GetRepoEntityByIdRequest<T> request) throws NonExistentRepoEntityException;
    GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request);
    GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request);
    GetUnusedRepoEntitysResponse<T> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<T> request);
    GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request);
}
