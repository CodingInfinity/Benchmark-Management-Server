package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.GetRepoEntityByIdRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import com.codinginfinity.benchmark.managenent.service.exception.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class GetRepoEntityByIdTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {
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
    public void getRepoEntityThatDoesNotExistTest(){
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        T entity = repositoryEntityManagement.getRepoEntityById(new GetRepoEntityByIdRequest<T>(getExpectedId())).getRepoEntity();
    }

    @Test
    public void getRepoEntityTest(){
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.of(getRepoEntity()));
        T entity = repositoryEntityManagement.getRepoEntityById(new GetRepoEntityByIdRequest<T>(getExpectedId())).getRepoEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
