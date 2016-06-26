package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.request.GetDatasetCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/25.
 */
public class GetDatasetCategoryTest extends AbstractTest {


    @Inject
    @InjectMocks
    private DatasetCategoryManagement categoryManagement;

    @Mock
    private DatasetCategoryRepository categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getNonExistantDatasetCategoryTest() {
        DatasetCategory category = new DatasetCategory(1L, "Test");
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.empty());

        categoryManagement.getDatasetCategory(new GetDatasetCategoryRequest(1L));
        thrown.expect(NonExistentException.class);
    }


    @Test
    public void getDatasetCategoryTest() {
        DatasetCategory category = new DatasetCategory(1L, "Test");
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.of(category));

        DatasetCategory savedCategory = categoryManagement.getDatasetCategory(new GetDatasetCategoryRequest(1L)).getCategory();
        assertEquals("Test", savedCategory.getName());
        assertEquals(new Long(1L), category.getId());
    }
}
