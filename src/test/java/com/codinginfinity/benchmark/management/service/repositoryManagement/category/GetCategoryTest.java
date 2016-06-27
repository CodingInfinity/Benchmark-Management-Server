package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.GetCategoryResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class GetCategoryTest<T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>> extends AbstractCategoryTest<T> {

    @Inject
    @InjectMocks
    private R categoryManagement;

    @Mock
    private S categoryRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getNonExistantCategoryTest() throws NonExistentException {
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.empty());

        categoryManagement.getCategory(new GetCategoryRequest<T>(getExpectedId()));
        thrown.expect(NonExistentException.class);
    }


    @Test
    public void getCategoryTest() throws NonExistentException {
        T category = getCategory();
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.of(category));

        T savedCategory = categoryManagement.getCategory(new GetCategoryRequest<T>(getExpectedId())).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals(getExpectedName(), savedCategory.getName());
    }
}
