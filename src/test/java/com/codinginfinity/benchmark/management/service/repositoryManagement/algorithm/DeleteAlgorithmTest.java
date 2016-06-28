package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.AddRepoEntityTest;
import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
public class DeleteAlgorithmTest extends AddRepoEntityTest<AlgorithmCategory, Algorithm,
        AlgorithmRepository,
        AlgorithmManagement> {
    @Override
    protected Long getExpectedId() {
        return new Long(12345);
    }

    @Override
    protected String getExpectedName() {
        return new String("Bubble Sort");
    }

    @Override
    protected String getExpectedDescription() {
        return new String("The standard Bubble Sort Algorithm");
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
        AlgorithmCategory sorting = new AlgorithmCategory(new Long(1), "Sorting");
        AlgorithmCategory ai = new AlgorithmCategory(new Long(2), "Artificial Intelligence");
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
