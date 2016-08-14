package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagementImpl;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.exception.DuplicateAlgorithmCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class AlgorithmCategoryManagementImpl extends CategoryManagementImpl<AlgorithmCategory, AlgorithmCategoryRepository> implements AlgorithmCategoryManagement {

    @Inject
    private AlgorithmCategoryRepository repository;

    @Override
    protected AlgorithmCategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    protected AlgorithmCategory newCategory() {
        return new AlgorithmCategory();
    }

    @Override
    protected DuplicateCategoryException getDuplicateCategoryException() {
        return new DuplicateAlgorithmCategoryException("Duplicate algorithm category");
    }

    @Override
    protected NonExistentCategoryException getNonExistentCategoryException() {
        return new NonExistentCategoryException("Algorithm category doesn't exist");
    }
}