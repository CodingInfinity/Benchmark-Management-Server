package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.UpdateRepoEntityMetadataRequest;
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
public abstract class UpdateRepoEntityMetadataTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    M repositoryEntityManagement;

    @Inject
    R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void updateRepoEntityThatDoesNotExistMetadataTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        repositoryEntityManagement.updateRepoEntityMetaData(new UpdateRepoEntityMetadataRequest<C,T>(getExpectedId(), getExpectedName(),getExpectedUser(), getExpectedCategories(), getExpectedDescription()));
    }

    @Test
    public void updateRepoEntityMetadataTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.of(getRepoEntity()));
        T entity = repositoryEntityManagement.updateRepoEntityMetaData(new UpdateRepoEntityMetadataRequest<C,T>(getExpectedId(), getExpectedName(),getExpectedUser(), getExpectedCategories(), getExpectedDescription())).getEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), getExpectedCategories().size());
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
