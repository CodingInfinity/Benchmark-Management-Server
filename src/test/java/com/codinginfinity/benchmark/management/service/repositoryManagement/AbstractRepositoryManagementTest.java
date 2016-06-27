package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.domain.User;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
public abstract class AbstractRepositoryManagementTest <C extends Category, T extends RepoEntity<C>> extends AbstractTest {
    protected abstract Long getExpectedId();

    protected abstract String getExpectedName();

    protected abstract String getExpectedDescription();

    protected abstract User getExpectedUser();

    protected abstract List<C> getExpectedCategories();

    protected abstract T getRepoEntity();
}
