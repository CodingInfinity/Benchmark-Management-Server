package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Abstract repository definition for the {@link RepoEntity} object. It is
 * afvised that objects that extend the {@link RepoEntity} object, to extend
 * this interface for implementing the associated repository. This will allow
 * to make use of the associated
 * {@link com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement}
 * service contract and reference implementation.
 *
 * @apiNote Repository is annotated with {@link @NoRepositoryBean} to ensure a
 * Java Bean is not created of this repository as the associated entity,
 * {@link RepoEntity} is declared abstract.
 *
 * @see com.codinginfinity.benchmark.management.domain.RepoEntity
 *
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@NoRepositoryBean
public interface RepoEntityRepository<T extends RepoEntity> extends JpaRepository<T, Long> {
    Optional<T> findOneById(Long id);

    List<T> findByName(String name);

    List<T> findByUser(User user);

    /**
     * Find all repository entities with a specified classifier.
     * @param categoryId Find all repository entities with the associated
     *                   classifier id.
     * @return Reruns a {@link List} of repository entities of which has the
     *          associated classifier.
     */
    List<T> findByCategory(@Param("categoryId") Long categoryId);
}
