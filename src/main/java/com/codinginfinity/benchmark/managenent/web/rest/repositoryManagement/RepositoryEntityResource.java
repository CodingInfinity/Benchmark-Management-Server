package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/29.
 */
@Slf4j
public abstract class RepositoryEntityResource <C extends Category, T extends RepoEntity<C>,
                                        R extends RepoEntityRepository<T>, M extends RepositoryEntityManagement<C, T>>{
    protected abstract R getRepository();

    protected abstract M getRepositoryEntityManagement();

    public ResponseEntity<?> addRepoEntity(AddRepoEntityRequest<C, T> request) {
        getRepositoryEntityManagement().addRepoEntity(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteRepoEntity(DeleteRepoEntityRequest<T> request) throws NonExistentRepoEntityException {
        getRepositoryEntityManagement().deleteRepoEntity(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<C, T> request) throws NonExistentRepoEntityException {
        getRepositoryEntityManagement().updateRepoEntityMetaData(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getRepoEntityById(Long id) throws NonExistentRepoEntityException {
        T entity = getRepositoryEntityManagement().getRepoEntityById(new GetRepoEntityByIdRequest<T>(id)).getRepoEntity();
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity<?> getRepoEntityByUsername(String userName) {
        List<T> entities = getRepositoryEntityManagement().getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(userName)).getEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}