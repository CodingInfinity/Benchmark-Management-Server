package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByUsernameRequest;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.response.GetUserWithAuthoritiesByLoginResponse;
import com.codinginfinity.benchmark.management.service.userManagement.response.GetUserWithAuthoritiesResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class GetRepoEntityByUsernameTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    private M repositoryEntityManagement;

    @Inject
    private R repoEntityRepository;

    @Inject
    private UserManagement userManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getRepoEntityByCatagoryNonExistentExceptionTest() throws NonExistentException {
        Mockito.when(userManagement.getUserWithAuthoritiesByLogin(Mockito.anyObject())).thenReturn(new GetUserWithAuthoritiesByLoginResponse(getExpectedUser()));
        Mockito.when(repoEntityRepository.findByUser(getExpectedUser())).thenReturn(new ArrayList<T>());

        thrown.expect(NonExistentRepoEntityException.class);
        List<T> responseEntities = repositoryEntityManagement.getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(getExpectedUser().getUsername())).getEntities();
    }


    @Test
    public void getRepoEntityByCatagoryTest() throws NonExistentException {
        List<T> entities = new ArrayList<T>();
        entities.add(getRepoEntity());
        T extra = getRepoEntity();
        extra.setId(555L);
        extra.setName("Concurrency");
        extra.setDescription("It does Concurrent Things");
        entities.add(extra);

        Mockito.when(userManagement.getUserWithAuthoritiesByLogin(Mockito.anyObject())).thenReturn(new GetUserWithAuthoritiesByLoginResponse(getExpectedUser()));
        Mockito.when(repoEntityRepository.findByUser(getExpectedUser())).thenReturn(entities);
        List<T> responseEntities = repositoryEntityManagement.getRepoEntityByUsername(new GetRepoEntityByUsernameRequest<T>(getExpectedUser().getUsername())).getEntities();
        Assert.assertEquals(entities.size(), responseEntities.size());
    }


    @Override
    protected User getExpectedUser() {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(false);
        user.setResetDate(null);
        user.setResetKey(null);
        return user;
    }

}
