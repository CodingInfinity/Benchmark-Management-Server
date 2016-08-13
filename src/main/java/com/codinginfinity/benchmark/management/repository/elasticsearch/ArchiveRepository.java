package com.codinginfinity.benchmark.management.repository.elasticsearch;

import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by andrew on 2016/07/06.
 */
@Repository
public interface ArchiveRepository extends ElasticsearchCrudRepository<Archive, String> {

    Optional<Archive> findOneById(String id);
}
