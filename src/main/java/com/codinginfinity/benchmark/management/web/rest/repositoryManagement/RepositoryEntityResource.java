package com.codinginfinity.benchmark.management.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.FileRepository;
import com.codinginfinity.benchmark.management.security.SecurityUtils;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.management.web.rest.dto.RepoEntityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Defines abstract RESTful API endpoints for all management related to
 * repository entities.
 *
 * @see com.codinginfinity.benchmark.management.domain.Category
 * @see com.codinginfinity.benchmark.management.domain.RepoEntity
 * @see com.codinginfinity.benchmark.management.repository.RepoEntityRepository
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @author Brenton Watt
 * @version 1.0.0
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
        RepoEntityDTO entity = getRepositoryEntityManagement().getRepoEntityById(new GetRepoEntityByIdRequest<T>(id)).getRepoEntityDTO();
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity<?> getRepoEntityByUsername(String userName) throws NonExistentException {
        List<T> entities = getRepositoryEntityManagement().getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(userName)).getEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    public ResponseEntity<?> getRepoEntityByCurrentUser() throws NonExistentException {
        List<T> entities = getRepositoryEntityManagement().getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(SecurityUtils.getCurrentUsername())).getEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllRepoEntities()throws NonExistentRepoEntityException
    {
        List<T> entities = getRepositoryEntityManagement().getAllRepoEntities(new GetAllRepoEntitiesRequest<T>()).getEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    public ResponseEntity<?> getRepoEntityContents(String id)throws NonExistentRepoEntityException{
        return new ResponseEntity<>(getRepositoryEntityManagement().getRepoEntityContent(new GetRepoEntityContentRequest(id)), HttpStatus.OK);
    }
}
