package com.codinginfinity.benchmark.management.test.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.repository.CategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetAllCategoriesRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.response.GetAllCategoriesResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/08/30.
 */
public abstract class GetAllCategoriesTest <T extends Category,
        S extends CategoryRepository<T>,
        R extends CategoryManagement<T>>  extends AbstractCategoryTest<T> {

    @Inject
    private R categoryManagement;

    @Inject
    private S categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getAllExistingCategoriesNonExistentRepoEntityExceptionTest() throws NonExistentCategoryException {
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<T>());
        thrown.expect(NonExistentCategoryException.class);

        categoryManagement.getAllCategories(new GetAllCategoriesRequest<T>());
    }

    @Test
    public void getAllExistingCategoriesTest() throws NonExistentCategoryException {
        Mockito.when(categoryRepository.findAll()).thenReturn(createCategories());

        GetAllCategoriesResponse<T> response = categoryManagement.getAllCategories(new GetAllCategoriesRequest<T>());

        Assert.assertEquals(3, response.getCategories().size());

        T category = response.getCategories().get(0);
        Assert.assertEquals(new Long(1L), category.getId());
        Assert.assertEquals("One", category.getName());

        category = response.getCategories().get(1);
        Assert.assertEquals(new Long(2L), category.getId());
        Assert.assertEquals("Two", category.getName());

        category = response.getCategories().get(2);
        Assert.assertEquals(new Long(3L), category.getId());
        Assert.assertEquals("Three", category.getName());
    }

    private List<T> createCategories() {
        List<T> categories = new ArrayList<>();
        categories.add(getNewCategory(1L, "One"));
        categories.add(getNewCategory(2L, "Two"));
        categories.add(getNewCategory(3L, "Three"));
        return categories;
    }
}
