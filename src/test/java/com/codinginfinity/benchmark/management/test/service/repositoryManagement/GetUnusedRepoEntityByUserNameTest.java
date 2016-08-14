package com.codinginfinity.benchmark.management.test.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/06/28.
 */
@Ignore
public abstract class GetUnusedRepoEntityByUserNameTest <C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    private M repositoryEntityManagement;

    @Inject
    private R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
