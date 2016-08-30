package com.codinginfinity.benchmark.management.test.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.GetRepoEntityByUsernameTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
public class GetAlgorithmByUsernameTest extends GetRepoEntityByUsernameTest<AlgorithmCategory, Algorithm,
        AlgorithmRepository,
        AlgorithmManagement> {
    @Override
    protected String getNonExistentExceptionMessage() {
        return "Algorithm does not Exist";
    }

    @Override
    protected Long getExpectedId() {
        return 12345L;
    }

    @Override
    protected String getExpectedName() {
        return "Bubble Sort";
    }

    @Override
    protected String getExpectedDescription() {
        return "The standard Bubble Sort Algorithm";
    }

    @Override
    protected User getExpectedUser() {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(false);
        user.setResetDate(null);
        user.setResetKey(null);
        return user;
    }

    @Override
    protected List<AlgorithmCategory> getExpectedCategories() {
        List<AlgorithmCategory> categories = new ArrayList<AlgorithmCategory>();
        AlgorithmCategory sorting = new AlgorithmCategory(1L, "Sorting");
        AlgorithmCategory ai = new AlgorithmCategory(2L, "Artificial Intelligence");
        categories.add(sorting);
        categories.add(ai);
        return categories;
    }

    @Override
    protected Algorithm getRepoEntity() {
        Algorithm algorithm = new Algorithm();
        algorithm.setCategories(getExpectedCategories());
        algorithm.setUser(getExpectedUser());
        algorithm.setId(getExpectedId());
        algorithm.setDescription(getExpectedDescription());
        algorithm.setName(getExpectedName());
        return algorithm;
    }
}
