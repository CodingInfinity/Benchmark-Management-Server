package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/29.
 */
public abstract class RepositoryEntityManagementImpl<C extends Category, T extends RepoEntity<C>, R extends RepoEntityRepository<T>>
    implements RepositoryEntityManagement<C,T>{
    protected abstract R getRepository();

    protected abstract T newRepoEntity();

    protected abstract NonExistentRepoEntityException getNonExistentRepoEntityException();

    @Override
    public AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) {
        R repository =  getRepository();
        T repoEntity = newRepoEntity();

        repoEntity.setName(request.getName());
        repoEntity.setDescription(request.getDescription());
        repoEntity.setUser(request.getUser());
        List<C> categories = request.getCategories();
        for (C category:categories) {
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