package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface AlgorithmManagement extends RepositoryEntityManagement<AlgorithmCategory, Algorithm>{
    @Override
    AddRepoEntityResponse<Algorithm> addRepoEntity(AddRepoEntityRequest<AlgorithmCategory, Algorithm> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException;;

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
    GetUnusedRepoEntitiesResponse<Algorithm> getUnusedRepoEntities(GetUnusedRepoEntitiesRequest<Algorithm> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Algorithm> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Algorithm> request);

    @Override
    GetAllEntitiesResponse<Algorithm> getAllEntities(GetAllEntitiesRequest<Algorithm> request)throws NonExistentRepoEntityException;
}
