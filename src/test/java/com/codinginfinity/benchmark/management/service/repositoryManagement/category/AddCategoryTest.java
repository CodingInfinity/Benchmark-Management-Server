package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception.DuplicateAlgorithmCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class AddCategoryTest<T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>>  extends AbstractCategoryTest<T> {

    @Inject
    @InjectMocks
    private R categoryManagement;

    @Mock
    private S categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addExistingCategoryTest() throws DuplicateCategoryException {
        T category = getCategory();
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.of(category));

        categoryManagement.addCategory(new AddCategoryRequest<T>(category.getName()));
        thrown.expect(DuplicateAlgorithmCategoryException.class);
    }

    @Test
    public void addNewCategoryTest() throws DuplicateCategoryException {
        T category = getCategory();
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        T savedCategory = categoryManagement.addCategory(new AddCategoryRequest<T>(category.getName())).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals(getExpectedName(), savedCategory.getName());
    }
}
