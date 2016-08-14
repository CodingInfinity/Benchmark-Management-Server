package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;

/**
 * Defines the service contract for the repository management module, specifically the submodule of category management
 * including all request, response and pre-conditions.
 * Important to note that all preconditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link CategoryManagementImpl} abstract class. It is advised that
 * any new category objects in the system should extend the {@link CategoryManagementImpl} abstract class instead of
 * implementing this service contract directly, This class is coded using generics providing the user with full
 * functionality by simply extending the class.
 *
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */


public interface CategoryManagement<T extends Category> {

    /**
     * Adds a new category of type T to the system.
     * @param request The request encapsulated as an {@link AddCategoryRequest} object.
     * @return Returns the result in an encapsulated {@link AddCategoryResponse} object.
     * @throws DuplicateCategoryException Thrown when a category of type T with a duplicate name already exists in the
     *     persistence layer.
     * @since 1.0.0
     */
    AddCategoryResponse<T> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException;

    /**
     * Delete a category from the system.
     * @param request The request encapsulated as an {@link DeleteCategoryRequest} object.
     * @return Returns the result in an encapsulated {@link DeleteCategoryResponse} object.
     * @throws NonExistentCategoryException Thrown when a category of type T is requested to be deleted from the system
     *     that doesn't exist.
     * @since 1.0.0
     */
    DeleteCategoryResponse<T> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException;

    /**
     * Updates a category's associated metadata.
     * @param request The request encapsulated as an {@link UpdateCategoryRequest} object.
     * @return Returns the result in an encapsulated {@link UpdateCategoryResponse} object.
     * @throws NonExistentCategoryException  Thrown when the metadata of a category of type T is requested to be updated
     *     that doesn't exist.
     * @since 1.0.0
     */
    UpdateCategoryResponse<T> updateCategory(UpdateCategoryRequest<T> request) throws NonExistentCategoryException;

    /**
     * Retrieves a category from the persistence layer by ID.
     * @param request The request encapsulated as an {@link GetCategoryByIdRequest} object.
     * @return Returns the result in an encapsulated {@link GetCategoryByIdResponse} object.
     * @throws NonExistentCategoryException Thrown when a no category with the associated ID exists.
     * @since 1.0.0
     */
    GetCategoryByIdResponse<T> getCategoryById(GetCategoryByIdRequest<T> request) throws NonExistentCategoryException;

    /**
     * Retrieves a category from the persistence layer by category name.
     * @param request The request encapsulated as an {@link GetCategoryByNameRequest} object.
     * @return Returns the result in an encapsulated {@link GetCategoryByNameResponse} object.
     * @throws NonExistentCategoryException Thrown when a no category with the associated name exists.
     * @since 1.0.0
     */
    GetCategoryByNameResponse<T> getCategoryByName(GetCategoryByNameRequest<T> request) throws NonExistentCategoryException;
    GetAllCategoriesResponse<T> getAllCategories(GetAllCategoriesRequest<T> request) throws NonExistentCategoryException;
}
