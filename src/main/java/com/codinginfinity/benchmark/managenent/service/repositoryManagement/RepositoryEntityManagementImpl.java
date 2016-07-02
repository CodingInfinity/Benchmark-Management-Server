package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.GetUserWithAuthoritiesRequest;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/29.
 */
public abstract class RepositoryEntityManagementImpl<C extends Category,
        T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        S extends CategoryManagement<C>> implements RepositoryEntityManagement<C,T>{
    protected abstract R getRepository();

    protected abstract T newRepoEntity();

    protected abstract S getCategoryManagement();

    protected abstract NonExistentRepoEntityException getNonExistentRepoEntityException();

    @Inject
    private UserManagement userManagement;

    @Override
    public AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) throws NonExistentException {
        R repository =  getRepository();
        T repoEntity = newRepoEntity();

        repoEntity.setName(request.getName());
        repoEntity.setDescription(request.getDescription());
        User currentUser = userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser();
        repoEntity.setUser(currentUser);
        for (Long categoryId : request.getCategories()) {
            C category = getCategoryManagement().getCategoryById(new GetCategoryByIdRequest<C>(categoryId)).getCategory();
            repoEntity.addCategory(category);
        }
        repository.save(repoEntity);
        return new AddRepoEntityResponse<T>(repoEntity);
    }

    @Override
    public DeleteRepoEntityResponse<T> deleteRepoEntity(DeleteRepoEntityRequest<T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }

        repository.delete(request.getId());
        return new DeleteRepoEntityResponse<T>(entityDoesExist.get());
    }

    @Override
    public UpdateRepoEntityMetadataResponse<T> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<C, T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }
        entityDoesExist.get().setName(request.getName());
        entityDoesExist.get().setUser(request.getUser());
        entityDoesExist.get().setDescription(request.getDescription());
        List<C> oldCategories = entityDoesExist.get().getCategories();
        oldCategories.clear();
        List<C> categories = request.getCategories();
        for (C category: categories) {
            entityDoesExist.get().addCategory(category);
        }
        return new UpdateRepoEntityMetadataResponse<T>(entityDoesExist.get());
    }

    @Override
    public GetRepoEntityByIdResponse<T> getRepoEntityById(GetRepoEntityByIdRequest<T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }
        return new GetRepoEntityByIdResponse<T>(entityDoesExist.get());
    }

    @Override
    public GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request){
        R repository = getRepository();
        List<T> entities = repository.findByUser(request.getUsername());
        return new GetRepoEntityByUsernameResponse<>(entities);
    }

    @Override
    public GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntitysResponse<T> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request) {
        return null;
    }
}
