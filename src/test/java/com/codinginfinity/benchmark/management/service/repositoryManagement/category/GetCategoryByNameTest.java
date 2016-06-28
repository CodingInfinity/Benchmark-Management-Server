package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByNameRequest;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class GetCategoryByNameTest<T extends Category,
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
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        thrown.expect(NonExistentException.class);
        categoryManagement.getCategoryByName(new GetCategoryByNameRequest<T>(getExpectedName()));
    }


    @Test
    public void getCategoryByIdTest() throws NonExistentException {
        T category = getCategory();
        when(categoryRepository.findOneByName(getExpectedName())).thenReturn(Optional.of(category));

        T savedCategory = categoryManagement.getCategoryByName(new GetCategoryByNameRequest<T>(getExpectedName())).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals(getExpectedName(), savedCategory.getName());
    }
}
