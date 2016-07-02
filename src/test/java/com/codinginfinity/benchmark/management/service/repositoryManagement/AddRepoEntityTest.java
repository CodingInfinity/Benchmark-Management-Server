package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.GetCategoryByIdResponse;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.AddRepoEntityRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.GetUserWithAuthoritiesResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;

/**
 * Created by reinhardt on 2016/06/27.
 */
public abstract class AddRepoEntityTest<C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>,
        S extends CategoryManagement<C>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    private M repositoryEntityManagement;

    @Inject
    private R repoEntityRepository;

    @Inject
    private UserManagement userManagement;

    @Inject
    private S categoryManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addRepoEntityTest() throws NonExistentException {
        Mockito.when(repoEntityRepository.save((T)any())).thenAnswer(invocation -> {
            T entity = (T)invocation.getArguments()[0];
            entity.setId(getExpectedId());
            return entity;
        });

        Mockito.when(userManagement.getUserWithAuthorities(anyObject()))
                .thenReturn(new GetUserWithAuthoritiesResponse(getExpectedUser()));

        Mockito.when(categoryManagement.getCategoryById(anyObject())).thenAnswer(invocationOnMock -> {
            List<C> categories = getExpectedCategories();
            GetCategoryByIdRequest request = (GetCategoryByIdRequest)invocationOnMock.getArguments()[0];
            for (C category : categories) {
                if (category.getId().equals(request.getId()))
                    return new GetCategoryByIdResponse<>(category);
            }
            throw new NonExistentCategoryException();
        });

        List<C> expectedCategories = getExpectedCategories();
        List<Long> expectedCategoriesIds = new ArrayList<>(expectedCategories.size());
        expectedCategories.forEach(category -> expectedCategoriesIds.add(category.getId()));

        T entity = (T)repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<C,T>(getExpectedName(),
                expectedCategoriesIds, getExpectedDescription())).getRepoEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }
}
