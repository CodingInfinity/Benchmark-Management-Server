package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.*;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

/**
 * A reference implementation of the {@link CategoryManagement} service contract. This class is coded using generics
 * allowing a user to simply extend this class to obtain full management functionality for categories in the backend
 * system. It is advised to extend this class rather than implementing the {@link CategoryManagement} service contract
 * directly.
 *
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request
 * @see com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */


@Secured(AuthoritiesConstants.ADMIN)
public abstract class CategoryManagementImpl<T extends Category, V extends CategoryRepository<T>>  implements CategoryManagement<T> {

    @Override
    public AddCategoryResponse<T> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException {
        V repository = getRepository();

        Optional<T> categoryExists = repository.findOneByName(request.getName());
        if (categoryExists.isPresent()) {
            throw getDuplicateCategoryException();
        }

        T category = newCategory();
        category.setName(request.getName());
        category = getRepository().save(category);
        return new AddCategoryResponse<>(category);
    }

    @Override
    public DeleteCategoryResponse<T> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException {
        V repository = getRepository();

        Optional<T> categoryExists = repository.findOneById(request.getId());
        if (!categoryExists.isPresent()) {
            throw getNonExistentCategoryException();
        }

        getRepository().delete(request.getId());
        return new DeleteCategoryResponse<T>(categoryExists.get());
    }

    @Override
    public UpdateCategoryResponse<T> updateCategory(UpdateCategoryRequest<T> request) throws NonExistentCategoryException {
        V repository = getRepository();

        Optional<T> categoryExists = repository.findOneById(request.getId());
        if (!categoryExists.isPresent()) {
            throw getNonExistentCategoryException();
        }

        T category = categoryExists.get();
        category.setName(request.getName());
        repository.save(category);
        return new UpdateCategoryResponse<>(category);
    }

    @Override
    public GetCategoryByIdResponse<T> getCategoryById(GetCategoryByIdRequest<T> request) throws NonExistentCategoryException {
        V repository = getRepository();

        Optional<T> categoryExists = repository.findOneById(request.getId());
        if (!categoryExists.isPresent()) {
            throw getNonExistentCategoryException();
        }

        return new GetCategoryByIdResponse<>(categoryExists.get());
    }

    @Override
    public GetCategoryByNameResponse<T> getCategoryByName(GetCategoryByNameRequest<T> request) throws NonExistentCategoryException {
        V repository = getRepository();

        Optional<T> categoryExists = repository.findOneByName(request.getName());
        if (!categoryExists.isPresent()) {
            throw getNonExistentCategoryException();
        }

        return new GetCategoryByNameResponse<>(categoryExists.get());
    }

    @Override
    @Secured(AuthoritiesConstants.USER)
    public GetAllCategoriesResponse<T> getAllCategories (GetAllCategoriesRequest<T> request)throws NonExistentCategoryException {
        V repository = getRepository();

        List<T> categories = repository.findAll();
        if (categories.isEmpty()) {
            throw getNonExistentCategoryException();
        }

        return new GetAllCategoriesResponse<>(categories);
    }

    protected abstract V getRepository();

    protected abstract T newCategory();

    protected abstract DuplicateCategoryException getDuplicateCategoryException();

    protected abstract NonExistentCategoryException getNonExistentCategoryException();
}
