package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RepoManagementConfiguration.class)
public abstract class AbstractRepositoryManagementTest <C extends Category, T extends RepoEntity<C>>{
    protected abstract Long getExpectedId();

    protected abstract String getExpectedName();

    protected abstract String getExpectedDescription();

    protected abstract User getExpectedUser();

    protected abstract List<C> getExpectedCategories();

    protected abstract T getRepoEntity();

    protected abstract String getNonExistentExceptionMessage();
}
