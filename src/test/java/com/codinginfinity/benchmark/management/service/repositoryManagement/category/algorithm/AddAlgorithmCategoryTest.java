package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception.DuplicateAlgorithmCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.request.AddAlgorithmCategoryRequest;
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
public class AddAlgorithmCategoryTest extends AbstractTest {

    @Inject
    @InjectMocks
    private AlgorithmCategoryManagement categoryManagement;

    @Mock
    private AlgorithmCategoryRepository categoryRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addExistingCategoryTest() {
        AlgorithmCategory category = new AlgorithmCategory(1L, "Test");
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.of(category));

        categoryManagement.addAlgorithmCategory(new AddAlgorithmCategoryRequest("Test"));
        thrown.expect(DuplicateAlgorithmCategoryException.class);
    }


    @Test
    public void addNewCategoryTest() {
        AlgorithmCategory category = new AlgorithmCategory(1L, "Test");
        when(categoryRepository.findOneByName(anyString())).thenReturn(Optional.empty());

        categoryManagement.addAlgorithmCategory(new AddAlgorithmCategoryRequest("Test"));
    }
}