package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;

/**
 * Created by andrew on 2016/06/25.
 */
public class DatasetCategoryTest extends AbstractCategoryTest {

    @Override
    protected <T extends Category> T getCategory() {
        return (T)new DatasetCategory();
    }
}
