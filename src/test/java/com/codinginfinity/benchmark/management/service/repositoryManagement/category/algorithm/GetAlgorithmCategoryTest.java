package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.request.GetAlgorithmCategoryRequest;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
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
public class GetAlgorithmCategoryTest extends AbstractTest {

    @Inject
    @InjectMocks
    private AlgorithmCategoryManagement categoryManagement;

    @Mock
    private AlgorithmCategoryRepository categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getNonExistantAlgorithmCategoryTest() {
        AlgorithmCategory category = new AlgorithmCategory(1L, "Test");
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.empty());

        categoryManagement.getAlgorithmCategory(new GetAlgorithmCategoryRequest(1L));
        thrown.expect(NonExistentException.class);
    }


    @Test
    public void getAlgorithmCategoryTest() {
        AlgorithmCategory category = new AlgorithmCategory(1L, "Test");
        when(categoryRepository.findOneById(anyLong())).thenReturn(Optional.of(category));

        AlgorithmCategory savedCategory = categoryManagement.getAlgorithmCategory(new GetAlgorithmCategoryRequest(1L)).getCategory();
        assertEquals("Test", savedCategory.getName());
        assertEquals(new Long(1L), category.getId());
    }
}
