package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/27.
 */
@NoRepositoryBean
public interface RepoEntityRepository<T extends RepoEntity> extends JpaRepository<T, Long> {
    Optional<T> findOneById(Long id);

    List<T> findByName(String name);

    List<T> findByUser(User user);

    List<T> findByCategory(@Param("categoryId") Long categoryId);
}
