package com.codinginfinity.benchmark.management.test.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.LinkedException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.DeleteCategoryRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class DeleteCategoryTest<T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>,
        M extends RepoEntity<T>,
        N extends RepositoryEntityManagement<T, M>>  extends AbstractCategoryTest<T> {

    @Inject
    private R categoryManagement;

    @Inject
    private S categoryRepository;

    @Inject
    private N repositoryEntityManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deleteNonExistantCategoryTest() throws NonExistentException, LinkedException {
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        thrown.expect(NonExistentCategoryException.class);
        categoryManagement.deleteCategory(new DeleteCategoryRequest<T>(getExpectedId()));
    }

    @Test
    public void deleteCategoryTest() throws NonExistentException, LinkedException {
        T category = getCategory();
        HashMap<Long, T> database = new HashMap<>();
        database.put(category.getId(), category);

        doAnswer(args -> database.remove((Long)args.getArguments()[0])).when(categoryRepository).delete(getExpectedId());
        when(categoryRepository.findOneById(anyLong())).thenAnswer(args -> Optional.ofNullable(database.get((Long) args.getArguments()[0])));
        when(repositoryEntityManagement.getRepoEntityByCategory(any(GetRepoEntityByCategoryRequest.class))).thenThrow(new NonExistentRepoEntityException());

        T savedCategory = categoryManagement.getCategoryById(new GetCategoryByIdRequest<T>(getExpectedId())).getCategory();
        assertNotNull(savedCategory);
        assertEquals(getExpectedId(), savedCategory.getId());
        assertEquals(getExpectedName(), savedCategory.getName());

        T deletedEntity = categoryManagement.deleteCategory(new DeleteCategoryRequest<T>(getExpectedId())).getCategory();
        assertEquals(getExpectedId(), deletedEntity.getId());
        assertEquals(getExpectedName(), deletedEntity.getName());

        thrown.expect(NonExistentCategoryException.class);
        savedCategory = categoryManagement.getCategoryById(new GetCategoryByIdRequest<T>(getExpectedId())).getCategory();
        assertNull(savedCategory);
    }
}
