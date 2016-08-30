package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.ArchiveRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetAllRepoEntitiesRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.response.GetAllRepoEntitiesResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/08/30.
 */
public abstract class GetAllRepoEntitiesTest <C extends Category, T extends RepoEntity<C>,
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
    public void getAllRepoEntitiesNonExistentExceptionTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findAll()).thenReturn(new ArrayList<T>());
        thrown.expect(NonExistentRepoEntityException.class);
        repositoryEntityManagement.getAllRepoEntities(new GetAllRepoEntitiesRequest<>());
    }

    @Test
    public void getAllRepoEntitiesTest() throws NonExistentRepoEntityException {
        List<T> entities = new ArrayList<>();
        T entity = getRepoEntity();
        entity.setId(555L);
        entity.setName("Concurrency");
        entity.setDescription("It does Concurrent Things");
        entities.add(entity);

        Mockito.when(repoEntityRepository.findAll()).thenReturn(entities);
        GetAllRepoEntitiesResponse<T> response = repositoryEntityManagement.getAllRepoEntities(new GetAllRepoEntitiesRequest<T>());

        Assert.assertEquals(1, response.getEntities().size());

        T repoEntity = response.getEntities().get(0);
        Assert.assertEquals(new Long(555L), repoEntity.getId());
        Assert.assertEquals("Concurrency", repoEntity.getName());
        Assert.assertEquals("It does Concurrent Things", repoEntity.getDescription());
    }
}
