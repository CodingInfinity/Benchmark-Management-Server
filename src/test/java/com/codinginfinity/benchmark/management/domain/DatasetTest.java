package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import org.junit.Test;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by reinhardt on 2016/06/26.
 */
public class DatasetTest extends RepoManagementTest<Dataset, DatasetRepository,DatasetCategory> {
    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(Dataset.class);
    }
}
