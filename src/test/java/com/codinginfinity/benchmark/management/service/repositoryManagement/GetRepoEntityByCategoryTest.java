package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.GetRepoEntityByCategoryRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class GetRepoEntityByCategoryTest <C extends Category, T extends RepoEntity<C>,
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

    /*
    @Test
    public void getRepoEntityByCatagoryTest(){
        List<T> entities = new ArrayList<T>();
        entities.add(getRepoEntity());
        T extra = getRepoEntity();
        extra.setId(new Long(555));
        extra.setName("Concurrency");
        extra.setDescription("It does Concurrent Things");
        entities.add(extra);
        Mockito.when(repoEntityRepository.findByCategory(getExpectedCategories().get(0))).thenReturn(entities);
        List<T> responseEntities = repositoryEntityManagement.getRepoEntityByCategory(new GetRepoEntityByCategoryRequest<C,T>(getExpectedCategories().get(0))).getEntities();
        T entityIn = entities.get(0);
        T entityOut = responseEntities.get(0);
        assertEquals(entityIn.getCategories().size(), entityOut.getCategories().size());
        assertTrue(entityOut.getCategories().contains(getExpectedCategories().get(0)));
        entityIn = entities.get(1);
        entityOut = responseEntities.get(1);
        assertEquals(entityIn.getCategories().size(), entityOut.getCategories().size());
        assertTrue(entityOut.getCategories().contains(getExpectedCategories().get(0)));
    }
    */
}
