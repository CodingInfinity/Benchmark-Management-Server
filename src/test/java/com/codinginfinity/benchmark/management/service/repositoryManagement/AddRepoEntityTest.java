package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.AddRepoEntityRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

/**
 * Created by reinhardt on 2016/06/27.
 */
public abstract class AddRepoEntityTest<T extends RepoEntity,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<T>> extends AbstractRepositoryManagementTest<T> {

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
    public void addRepoEntityTest(){
        Mockito.when(repoEntityRepository.save((T)any())).thenAnswer(invocation -> {
            T entity = (T)invocation.getArguments()[0];
            entity.setId(getExpectedId());
            return entity;
        });

        T entity = repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<T>(getExpectedName(),
                getExpectedUser(), getExpectedCategories(), getExpectedDescription())).getRepoEntity();
        assertEquals(entity.getId(), new Long(12345));
       // assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
