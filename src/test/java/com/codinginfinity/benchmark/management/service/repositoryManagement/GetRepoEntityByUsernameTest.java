package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByUsernameRequest;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/28.
 */
@Ignore
public abstract class GetRepoEntityByUsernameTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    M repositoryEntityManagement;

    @Inject
    R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getRepoEntityByCatagoryTest() throws NonExistentRepoEntityException {
        List<T> entities = new ArrayList<T>();
        entities.add(getRepoEntity());
        T extra = getRepoEntity();
        extra.setId(new Long(555));
        extra.setName("Concurrency");
        extra.setDescription("It does Concurrent Things");
        entities.add(extra);
        Mockito.when(repoEntityRepository.findByUser(getExpectedUser().getUsername())).thenReturn(entities);
        List<T> responseEntities = repositoryEntityManagement.getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(getExpectedUser().getUsername())).getEntities();
        assertEquals(entities.size(), responseEntities.size());
    }
}
