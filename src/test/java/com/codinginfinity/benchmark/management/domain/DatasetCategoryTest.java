package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import org.junit.Test;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by andrew on 2016/06/25.
 */
public class DatasetCategoryTest extends AbstractCategoryTest {

    @Override
    protected <T extends Category> T getCategory() {
        return (T)new DatasetCategory();
    }

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(DatasetCategory.class);
    }
}
