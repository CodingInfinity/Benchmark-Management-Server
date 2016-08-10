package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class GetCategoryByIdTest<T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>>  extends AbstractCategoryTest<T> {

    @Inject
    private R categoryManagement;

    @Inject
    private S categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getNonExistantCategoryTest() throws NonExistentException {
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.empty());

        thrown.expect(NonExistentException.class);
        categoryManagement.getCategoryById(new GetCategoryByIdRequest<T>(getExpectedId()));

    }


    @Test
    public void getCategoryByIdTest() throws NonExistentException {
        T category = getCategory();
        when(categoryRepository.findOneById(getExpectedId())).thenReturn(Optional.of(category));

        T savedCategory = categoryManagement.getCategoryById(new GetCategoryByIdRequest<T>(getExpectedId())).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals(getExpectedName(), savedCategory.getName());
    }
}
