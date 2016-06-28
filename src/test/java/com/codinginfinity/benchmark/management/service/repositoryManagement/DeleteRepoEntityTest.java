package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.DeleteRepoEntityRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class DeleteRepoEntityTest<C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T>{
    @InjectMocks
    @Inject
    M repositoryEntityManagement;

    @Mock
    R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteNonExistentRepoEntityTest(){
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        repositoryEntityManagement.deleteRepoEntity(new DeleteRepoEntityRequest<T>(getExpectedId()));
    }

    @Test
    public void deleteRepoEntityTest(){
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.of(getRepoEntity()));
        T entity = repositoryEntityManagement.deleteRepoEntity(new DeleteRepoEntityRequest<T>(getExpectedId())).getEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
