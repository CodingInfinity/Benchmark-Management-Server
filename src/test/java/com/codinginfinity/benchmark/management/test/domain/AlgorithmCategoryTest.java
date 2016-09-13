package com.codinginfinity.benchmark.management.test.domain;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import org.junit.Test;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by andrew on 2016/06/25.
 */
public class AlgorithmCategoryTest extends AbstractCategoryTest<AlgorithmCategory> {

    @Override
    protected AlgorithmCategory getCategory() {
        return new AlgorithmCategory();
    }

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(AlgorithmCategory.class);
    }
}
