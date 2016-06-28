package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/27.
 */
@NoRepositoryBean
public interface RepoEntityRepository<T extends RepoEntity> extends JpaRepository<T, Long> {
    Optional<T> findOneById(Long id);

    List<T> findByName(String name);

    List<T> findByUser(String user);

    /*
    @Query("SELECT * FROM User u WHERE u.")
    List<T> findByCategory(Category category);
    */
}
