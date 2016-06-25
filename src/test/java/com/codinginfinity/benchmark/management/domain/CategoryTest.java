package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.Category;

/**
 * Created by andrew on 2016/06/25.
 */
public class CategoryTest extends AbstractCategoryTest {

    @Override
    protected <T extends Category> T getCategory() {
        return (T)new Category();
    }
}
