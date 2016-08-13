package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/26.
 */
@NoRepositoryBean
public interface CategoryRepository<T extends Category> extends JpaRepository<T, Long> {

    Optional<T> findOneByName(String name);

    Optional<T> findOneById(Long name);

    List<T> findAll();
}
