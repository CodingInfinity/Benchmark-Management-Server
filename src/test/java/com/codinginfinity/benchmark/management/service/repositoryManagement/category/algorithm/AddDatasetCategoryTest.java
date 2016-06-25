package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.DatasetCategoryManagement;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/25.
 */
public class AddDatasetCategoryTest extends AbstractTest {

    @Inject
    @InjectMocks
    private DatasetCategoryManagement categoryManagement;

    @Mock
    private DatasetCategoryRepository datasetCategoryRepository;


}
