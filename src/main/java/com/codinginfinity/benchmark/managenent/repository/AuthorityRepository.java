package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrew on 2016/06/15.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
