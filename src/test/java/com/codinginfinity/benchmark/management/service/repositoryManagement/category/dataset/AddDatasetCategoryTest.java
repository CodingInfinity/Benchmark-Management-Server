package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception.DuplicateAlgorithmCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.request.AddDatasetCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/25.
 */
public class AddDatasetCategoryTest extends AbstractTest {

    @Inject
    @InjectMocks
    private DatasetCategoryManagement categoryManagement;

    @Mock
    private DatasetCategoryRepository categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addExistingCategoryTest() {
        DatasetCategory category = new DatasetCategory(1L, "Test");
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.of(category));

        categoryManagement.addDatasetCategory(new AddDatasetCategoryRequest("Test"));
        thrown.expect(DuplicateAlgorithmCategoryException.class);
    }

    @Test
    public void addNewCategoryTest() {
        DatasetCategory category = new DatasetCategory(1L, "Test");
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        categoryManagement.addDatasetCategory(new AddDatasetCategoryRequest("Test"));
    }
}
