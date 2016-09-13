package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.ArchiveRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByIdRequest;
import com.codinginfinity.benchmark.management.web.rest.dto.RepoEntityDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class GetRepoEntityByIdTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    M repositoryEntityManagement;

    @Inject
    R repoEntityRepository;

    @Inject
    ArchiveRepository archiveRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getRepoEntityThatDoesNotExistTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        RepoEntityDTO entity = repositoryEntityManagement.getRepoEntityById(new GetRepoEntityByIdRequest<T>(getExpectedId())).getRepoEntityDTO();
    }

    @Test
    public void getRepoEntityTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.of(getRepoEntity()));
        Mockito.when(archiveRepository.findOneById(Mockito.anyString())).thenReturn(Optional.of(new Archive()));
        RepoEntityDTO entity = repositoryEntityManagement.getRepoEntityById(new GetRepoEntityByIdRequest<T>(getExpectedId())).getRepoEntityDTO();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getId(), getExpectedId());
        //assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
