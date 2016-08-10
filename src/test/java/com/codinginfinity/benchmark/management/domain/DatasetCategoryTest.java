package com.codinginfinity.benchmark.management.domain;

import org.junit.Test;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by andrew on 2016/06/25.
 */
public class DatasetCategoryTest extends AbstractCategoryTest<DatasetCategory> {

    @Override
    protected DatasetCategory getCategory() {
        return new DatasetCategory();
    }

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(DatasetCategory.class);
    }
}
