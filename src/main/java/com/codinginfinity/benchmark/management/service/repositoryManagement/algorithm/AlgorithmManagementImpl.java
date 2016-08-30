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
 * A reference implementation of the {@link AlgorithmManagement} interface and
 * the {@link RepositoryEntityManagementImpl} abstract class.
 *
 * @see com.codinginfinity.benchmark.management.service.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.response
 *
 * Created by andrew on 2016/06/24.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Service
public class AlgorithmManagementImpl
    extends RepositoryEntityManagementImpl<AlgorithmCategory, Algorithm, AlgorithmRepository, AlgorithmCategoryManagement>
    implements AlgorithmManagement {

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
