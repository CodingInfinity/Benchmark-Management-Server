package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.UpdateCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class UpdateCategoryTest<T extends Category,
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
    public void updateNonExistingCategoryTest() throws NonExistentException {
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.empty());

        categoryManagement.updateCategory(new UpdateCategoryRequest<T>(getExpectedId(), getExpectedName()));
        thrown.expect(NonExistentException.class);
    }

    @Test
    public void updateExistingCategory() throws NonExistentException {
        T category = getCategory();
        HashMap<Long, T> database = new HashMap<>();
        database.put(category.getId(), category);

        doAnswer(args -> database.remove((Long)args.getArguments()[0])).when(categoryRepository).delete(getExpectedId());
        when(categoryRepository.getOne(anyLong())).thenAnswer(args -> database.get((Long)args.getArguments()[0]));

        when(categoryRepository.findOneById(getExpectedId())).thenReturn(Optional.of(category));

        T savedCategory = categoryManagement.updateCategory(new UpdateCategoryRequest<T>(getExpectedId(), "Second Test")).getCategory();
        assertEquals(getExpectedId(), category.getId());
        assertEquals("Second Test", savedCategory.getName());
    }
}
