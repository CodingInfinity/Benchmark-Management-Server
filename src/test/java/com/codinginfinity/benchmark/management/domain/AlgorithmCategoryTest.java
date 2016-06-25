package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.Category;
import org.junit.Test;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by andrew on 2016/06/25.
 */
public class AlgorithmCategoryTest extends AbstractCategoryTest {

    @Override
    protected <T extends Category> T getCategory() {
        return (T)new AlgorithmCategory();
    }

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(AlgorithmCategory.class);
    }
}
