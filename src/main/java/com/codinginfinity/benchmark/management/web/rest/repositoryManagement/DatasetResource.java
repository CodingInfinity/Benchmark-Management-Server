package com.codinginfinity.benchmark.management.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.repository.DatasetRepository;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.DeleteRepoEntityRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityContentRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.UpdateRepoEntityMetadataRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;

/**
 * Defines RESTful API endpoints for all management related to dataset
 * repository entities.
 *
 * @see com.codinginfinity.benchmark.management.domain.DatasetCategory
 * @see com.codinginfinity.benchmark.management.domain.Dataset
 * @see com.codinginfinity.benchmark.management.repository.DatasetRepository
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement
 *
 * @author Fabio Loreggian
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @author Brenton Watt
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api/repo")
public class DatasetResource extends RepositoryEntityResource<DatasetCategory, Dataset, DatasetRepository, DatasetManagement>{

    @Inject
    private DatasetRepository repository;

    @Inject
    private DatasetManagement datasetManagement;

    @Override
    protected DatasetRepository getRepository() {
        return this.repository;
    }

    @Override
    protected DatasetManagement getRepositoryEntityManagement() {
        return this.datasetManagement;
    }

    @RequestMapping(value = "/dataset",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> addRepoEntity(
            @RequestParam("name") final String name,
            @RequestParam("description") final String description,
            @RequestParam("categories") final Long[] categories,
            @RequestParam("file") final MultipartFile multipartFile) throws  NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException {
        return super.addRepoEntity(name, description, categories, multipartFile);
    }

    @RequestMapping(value = "/dataset",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> deleteRepoEntity(DeleteRepoEntityRequest<Dataset> request) throws NonExistentRepoEntityException {
        return super.deleteRepoEntity(request);
    }

    @RequestMapping(value = "/dataset",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<DatasetCategory, Dataset> request) throws NonExistentRepoEntityException {
        return super.updateRepoEntityMetaData(request);
    }

    @RequestMapping(value = "/dataset/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityById(@PathVariable(value = "id") Long id) throws NonExistentRepoEntityException {
        return super.getRepoEntityById(id);
    }

    @RequestMapping(value = "/dataset/user/{userName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityByUsername(@PathVariable(value = "userName") String userName) throws NonExistentException {
        return super.getRepoEntityByUsername(userName);
    }

    @RequestMapping(value = "/dataset/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityByCurrentUser() throws NonExistentException {
        return super.getRepoEntityByCurrentUser();
    }

    @RequestMapping(value = "/datasets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getAllRepoEntities()throws NonExistentRepoEntityException{
        return super.getAllRepoEntities();
    }

    @RequestMapping(value = "/dataset/content/{id:.+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<?> getRepoEntityContents(@PathVariable(value = "id") String id)throws NonExistentRepoEntityException{
        return super.getRepoEntityContents(id);
    }
}
