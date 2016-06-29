package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.exception.NonExistentAlgorithmException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class AlgorithmManagementImpl
    extends RepositoryEntityManagementImpl<AlgorithmCategory, Algorithm, AlgorithmRepository>
    implements AlgorithmManagement{

    @Inject
    AlgorithmRepository algorithmRepository;

    @Override
    protected AlgorithmRepository getRepository() {
        return this.algorithmRepository;
    }

    @Override
    protected Algorithm newRepoEntity() {
        return new Algorithm();
    }

    @Override
    protected NonExistentRepoEntityException getNonExistentRepoEntityException() {
        return new NonExistentAlgorithmException("Algorithm does not Exist");
    }
}
