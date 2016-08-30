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
 * An interface extension of the {@link RepositoryEntityManagement} with generic
 * types of RepositoryEntityManagement<{@link AlgorithmCategory}, {@link Algorithm}>.
 *
 * A reference implementation is provided in {@link AlgorithmManagementImpl}.
 *
 * Created by andrew on 2016/06/25.
 *
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.response
 *
 * @author Reinhardt Cromhout
 * @author Andrew Broekman
 * @version 1.0.0
 */

public interface AlgorithmManagement extends RepositoryEntityManagement<AlgorithmCategory, Algorithm> {

    @Override
    AddRepoEntityResponse<Algorithm> addRepoEntity(AddRepoEntityRequest<AlgorithmCategory, Algorithm> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException;

    @Override
    DeleteRepoEntityResponse<Algorithm> deleteRepoEntity(DeleteRepoEntityRequest<Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    UpdateRepoEntityMetadataResponse<Algorithm> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<AlgorithmCategory, Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByIdResponse<Algorithm> getRepoEntityById(GetRepoEntityByIdRequest<Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByUsernameResponse<Algorithm> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Algorithm> request) throws NonExistentException;

    @Override
    GetRepoEntityByCategoryResponse<Algorithm> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<AlgorithmCategory, Algorithm> request) throws NonExistentRepoEntityException;

    @Override
    GetUnusedRepoEntitiesResponse<Algorithm> getUnusedRepoEntities(GetUnusedRepoEntitiesRequest<Algorithm> request);

    @Override
    GetUnusedRepoEntityByUsernameResponse<Algorithm> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<Algorithm> request);

    @Override
    GetAllRepoEntitiesResponse<Algorithm> getAllRepoEntities(GetAllRepoEntitiesRequest<Algorithm> request) throws NonExistentRepoEntityException;
}
