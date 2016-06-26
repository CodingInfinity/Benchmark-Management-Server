package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception.DuplicateAlgorithmCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.AddCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class DeleteCategoryTest <T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>> extends AbstractCategoryTest<T> {

    @Inject
    @InjectMocks
    private R categoryManagement;

    @Mock
    private S categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deleteNonExistantCategoryTest() throws NonExistentException {
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        categoryManagement.deleteCategory(new DeleteCategoryRequest<T>(getExpectedId()));
        thrown.expect(NonExistentException.class);
    }

    @Test
    public void deleteCategoryTest() throws NonExistentException {
        T category = getCategory();
        HashMap<Long, T> database = new HashMap<>();
        database.put(category.getId(), category);

        doAnswer(args -> database.remove((Long)args.getArguments()[0])).when(categoryRepository).delete(getExpectedId());
        when(categoryRepository.getOne(anyLong())).thenAnswer(args -> database.get((Long)args.getArguments()[0]));

        T savedCategory = categoryManagement.getCategory(new GetCategoryRequest<T>(getExpectedId())).getCategory();
        assertNotNull(savedCategory);
        assertEquals(getExpectedId(), savedCategory.getId());
        assertEquals(getExpectedName(), savedCategory.getName());

        categoryManagement.deleteCategory(new DeleteCategoryRequest<T>(getExpectedId()));
        savedCategory = categoryManagement.getCategory(new GetCategoryRequest<T>(getExpectedId())).getCategory();
        assertNull(savedCategory);
    }
}
