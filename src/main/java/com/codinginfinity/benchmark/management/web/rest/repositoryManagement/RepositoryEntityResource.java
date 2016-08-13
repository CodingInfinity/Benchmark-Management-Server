package com.codinginfinity.benchmark.management.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * Created by reinhardt on 2016/06/29.
 */
@Slf4j
public abstract class RepositoryEntityResource <C extends Category, T extends RepoEntity<C>,
                                        R extends RepoEntityRepository<T>, M extends RepositoryEntityManagement<C, T>>{
    protected abstract R getRepository();

    protected abstract M getRepositoryEntityManagement();

    public ResponseEntity<?> addRepoEntity(final String name,
                                           final String description,
                                           final Long[] categories,
                                           final MultipartFile multipartFile) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException {
        getRepositoryEntityManagement().addRepoEntity(new AddRepoEntityRequest<>(name, Arrays.asList(categories), description, multipartFile));
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

    public ResponseEntity<?> getAllRepoEntities()throws NonExistentRepoEntityException
    {
        List<T> entities = getRepositoryEntityManagement().getAllRepoEntities(new GetAllRepoEntitiesRequest<T>()).getEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
