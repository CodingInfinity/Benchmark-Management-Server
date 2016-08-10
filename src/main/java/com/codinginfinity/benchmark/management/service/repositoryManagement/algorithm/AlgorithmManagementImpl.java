package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.exception.NonExistentAlgorithmException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class AlgorithmManagementImpl
    extends RepositoryEntityManagementImpl<AlgorithmCategory, Algorithm, AlgorithmRepository, AlgorithmCategoryManagement>
    implements AlgorithmManagement{

    @Inject
    AlgorithmRepository algorithmRepository;

    @Inject
    AlgorithmCategoryManagement algorithmCategoryManagement;

    @Override
    protected AlgorithmRepository getRepository() {
        return this.algorithmRepository;
    }

    @Override
    protected Algorithm newRepoEntity() {
        return new Algorithm();
    }

    @Override
    protected AlgorithmCategoryManagement getCategoryManagement() {
        return this.algorithmCategoryManagement;
    }

    @Override
    protected NonExistentRepoEntityException getNonExistentRepoEntityException() {
        return new NonExistentAlgorithmException("Algorithm does not Exist");
    }
}
