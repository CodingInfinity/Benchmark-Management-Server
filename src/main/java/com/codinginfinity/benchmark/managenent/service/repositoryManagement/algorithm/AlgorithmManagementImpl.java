package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.exception.NonExistentAlgorithmException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
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
