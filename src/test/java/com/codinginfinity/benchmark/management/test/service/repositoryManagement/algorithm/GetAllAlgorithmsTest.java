package com.codinginfinity.benchmark.management.test.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.GetAllRepoEntitiesTest;

import java.util.List;

/**
 * Created by andrew on 2016/08/30.
 */
public class GetAllAlgorithmsTest extends GetAllRepoEntitiesTest<AlgorithmCategory, Algorithm,
        AlgorithmRepository,
        AlgorithmManagement> {

    @Override
    protected Long getExpectedId() {
        return null;
    }

    @Override
    protected String getExpectedName() {
        return null;
    }

    @Override
    protected String getExpectedDescription() {
        return null;
    }

    @Override
    protected User getExpectedUser() {
        return null;
    }

    @Override
    protected List<AlgorithmCategory> getExpectedCategories() {
        return null;
    }

    @Override
    protected Algorithm getRepoEntity() {
        return new Algorithm();
    }

    @Override
    protected String getNonExistentExceptionMessage() {
        return "Algorithm does not Exist";
    }
}
