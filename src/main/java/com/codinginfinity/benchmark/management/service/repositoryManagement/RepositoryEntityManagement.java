package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.response.*;

/**
 * Defines the service contract for the repository entity management module,
 * including all request objects, response objects, exceptions and pre-conditions.
 * Important to note that all pre-coditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link RepositoryEntityManagementImpl} class.
 *
 * @see com.codinginfinity.benchmark.management.service.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.response
 *
 * @author Reinhardt Cromhout
 * @version 1.0.0
 *
 * @param <C> A generic type that has to be a class that extends the {@link Category} abstract class.
 * @param <T> A generic type that has to be a class that extends the {@link RepoEntity} abstract class
 *            with a generic of type C.
 */
public interface RepositoryEntityManagement<C extends Category, T extends RepoEntity<C>> {

    /**
     * A new RepoEntity object is added to the systems database. This includes all
     * the metadata about the RepoEntity as well as the actual files that the
     * Entity consists of.
     *
     * @param request The request encapsulated as an {@link AddRepoEntityRequest} object.
     * @return Returns the result in an encapsulated {@link AddRepoEntityResponse} object.
     * @throws NoFileUploadedException Thrown when there is no file in the request object.
     * @throws NonExistentException
     * @throws FileFormatNotSupportedException Throw when the file in the request is not in a
     * valid format.
     * @throws CorruptedFileException Throw when the file in the request is corrupted.
     * @since 1.0.0
     */
    AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException;


    /**
     * The RepoEntity object specified in the request object is deleted form the
     * system. This also includes the meta data and the actual files.
     *
     * @param request The request encapsulated as an {@link DeleteRepoEntityRequest} object.
     * @return Returns the result in an encapsulated {@link DeleteRepoEntityResponse} object.
     * @throws NonExistentRepoEntityException Thrown when the specified RepoEntity does not exist.
     * @since 1.0.0
     */
    DeleteRepoEntityResponse<T> deleteRepoEntity(DeleteRepoEntityRequest<T> request) throws NonExistentRepoEntityException;


    /**
     * The Meta Data of a RepoEntity object is updated with the new meta data in the
     * request object.
     *
     * @param request The request encapsulated as an {@link UpdateRepoEntityMetadataRequest} object.
     * @return Returns the result in an encapsulated {@link UpdateRepoEntityMetadataResponse} object.
     * @throws NonExistentRepoEntityException Thrown when the specified RepoEntity does not exist.
     * @since 1.0.0
     */
    UpdateRepoEntityMetadataResponse<T> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<C,T> request) throws NonExistentRepoEntityException;


    /**
     * Gets and returns the RepoEntity with the specified ID.
     *
     * @param request The request encapsulated as an {@link GetRepoEntityByIdRequest} object.
     * @return Returns the result in an encapsulated {@link GetRepoEntityByIdResponse} object.
     * @throws NonExistentRepoEntityException Thrown when the specified RepoEntity does not exist.
     * @since 1.0.0
     */
    GetRepoEntityByIdResponse<T> getRepoEntityById(GetRepoEntityByIdRequest<T> request) throws NonExistentRepoEntityException;


    /**
     * Gets and returns the RepoEntitys that belong to a specific user.
     *
     * @param request The request encapsulated as an {@link GetRepoEntityByUsernameRequest} object.
     * @return Returns the result in an encapsulated {@link GetRepoEntityByUsernameResponse} object.
     * @since 1.0.0
     */
    GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request) throws NonExistentException;


    /**
     * Gets and returns the RepoEntitys that belong to a specific Category.
     *
     * @param request The request encapsulated as an {@link GetRepoEntityByCategoryRequest} object.
     * @return Returns the result in an encapsulated {@link GetRepoEntityByCategoryResponse} object.
     * @since 1.0.0
     */
    GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request);


    /**
     * Gets and returns the RepoEntitys that have not been used in any system
     * benchmarking jobs yet.
     *
     * @param request The request encapsulated as an {@link GetUnusedRepoEntitiesRequest} object.
     * @return Returns the result in an encapsulated {@link GetUnusedRepoEntitiesResponse} object.
     * @since 1.0.0
     */
    GetUnusedRepoEntitiesResponse<T> getUnusedRepoEntities(GetUnusedRepoEntitiesRequest<T> request);

    /**
     * Gets and returns the RepoEntitys that have not been used in any system
     * benchmarking jobs yet and that belong to s specific user.
     *
     * @param request The request encapsulated as an {@link GetUnusedRepoEntityByUsernameRequest} object.
     * @return Returns the result in an encapsulated {@link GetUnusedRepoEntityByUsernameResponse} object.
     * @since 1.0.0
     */
    GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request);

    /**
     * Gets and returns all the RepoEntities that belong to a specific user.
     *
     * @param request The request encapsulated as an {@link GetAllRepoEntitiesRequest} object.
     * @return Returns the result in an encapsulated {@link GetAllRepoEntitiesResponse} object.
     * @since 1.0.0
     */
    GetAllRepoEntitiesResponse<T> getAllRepoEntities(GetAllRepoEntitiesRequest<T> request) throws NonExistentRepoEntityException;
}
