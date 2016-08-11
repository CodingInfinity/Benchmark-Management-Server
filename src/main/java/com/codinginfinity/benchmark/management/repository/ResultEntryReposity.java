package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.ResultEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/08/11.
 */
@Repository
public interface ResultEntryReposity extends JpaRepository<ResultEntry, Long> {
    Optional<ResultEntry> findOneById(Long id);

    List<ResultEntry> findByTimestamp(ZonedDateTime timestamp);
}
