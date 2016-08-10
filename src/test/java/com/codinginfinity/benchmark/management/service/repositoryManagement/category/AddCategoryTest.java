package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.AddCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class AddCategoryTest<T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>>  extends AbstractCategoryTest<T> {

    @Inject
    private R categoryManagement;

    @Inject
    private S categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addExistingCategoryTest() throws DuplicateCategoryException {
       T category = getCategory();
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.of(category));

        thrown.expect(DuplicateCategoryException.class);
        categoryManagement.addCategory(new AddCategoryRequest<T>(category.getName()));

    }

    @Test
    public void addNewCategoryTest() throws DuplicateCategoryException {
       T category = getCategory();
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());
        when(categoryRepository.save(((T)any()))).thenAnswer(invocationOnMock -> {
            T savedCategory = (T)invocationOnMock.getArguments()[0];
            savedCategory.setId(9L);
            return savedCategory;
        });

        T savedCategory = categoryManagement.addCategory(new AddCategoryRequest<T>(category.getName())).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals(getExpectedName(), savedCategory.getName());
    }
}
