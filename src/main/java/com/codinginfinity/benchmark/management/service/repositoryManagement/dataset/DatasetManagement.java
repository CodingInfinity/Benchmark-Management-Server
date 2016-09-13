package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
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
 * types of RepositoryEntityManagement<{@link DatasetCategory}, {@link Dataset}>.
 *
 * A reference implementation is provided in {@link DatasetManagementImpl}.
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
public interface DatasetManagement extends RepositoryEntityManagement<DatasetCategory, Dataset> {

    @Override
    AddRepoEntityResponse<Dataset> addRepoEntity(AddRepoEntityRequest<DatasetCategory, Dataset> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException;

    @Override
    DeleteRepoEntityResponse<Dataset> deleteRepoEntity(DeleteRepoEntityRequest<Dataset> request) throws NonExistentRepoEntityException;

    @Override
    UpdateRepoEntityMetadataResponse<Dataset> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<DatasetCategory, Dataset> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByIdResponse<Dataset> getRepoEntityById(GetRepoEntityByIdRequest<Dataset> request) throws NonExistentRepoEntityException;

    @Override
    GetRepoEntityByUsernameResponse<Dataset> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<Dataset> request) throws NonExistentException;

    @Override
    GetRepoEntityByCategoryResponse<Dataset> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<DatasetCategory, Dataset> request) throws NonExistentRepoEntityException;

    @Override
    GetAllRepoEntitiesResponse<Dataset> getAllRepoEntities(GetAllRepoEntitiesRequest<Dataset> request) throws NonExistentRepoEntityException;
}
