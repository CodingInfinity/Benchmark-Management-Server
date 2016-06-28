package com.codinginfinity.benchmark.management.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by andrew on 2016/06/26.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public abstract class AbstractCategoryTest<T extends Category> {

    protected abstract Long getExpectedId();

    protected abstract String getExpectedName();

    protected abstract T getCategory();

    protected abstract String getDuplicateCategoryExceptionMessage();

    protected abstract String getNonExistentCategoryExceptionMessage();
}
