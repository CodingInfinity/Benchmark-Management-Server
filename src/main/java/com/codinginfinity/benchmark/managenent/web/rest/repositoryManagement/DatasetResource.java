package com.codinginfinity.benchmark.managenent.web.rest.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.managenent.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.managenent.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.DeleteRepoEntityRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.UpdateRepoEntityMetadataRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/06/29.
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
    public ResponseEntity<?> addRepoEntity(@RequestPart("name") final String name,
                                           @RequestPart("description") final String description,
                                           @RequestPart("categories") final Long[] categories,
                                           @RequestPart("file") final MultipartFile multipartFile) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException {
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
    public ResponseEntity<?> getRepoEntityByUsername(@PathVariable(value = "userName") String userName) {
        return super.getRepoEntityByUsername(userName);
    }
}
