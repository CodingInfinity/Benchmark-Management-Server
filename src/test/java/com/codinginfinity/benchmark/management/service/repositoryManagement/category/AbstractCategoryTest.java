package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;

/**
 * Created by andrew on 2016/06/26.
 */
public abstract class AbstractCategoryTest<T extends Category, S extends CategoryRepository<T>> extends AbstractTest {

    protected abstract Long getExpectedId();

    protected abstract String getExpectedName();

    protected abstract T getCategory();

    protected abstract S getRepo();

}
