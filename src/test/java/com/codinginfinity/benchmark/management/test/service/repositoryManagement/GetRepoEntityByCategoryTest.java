package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/06/28.
 */
@Ignore
public abstract class GetRepoEntityByCategoryTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    M repositoryEntityManagement;

    @Inject
    R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
