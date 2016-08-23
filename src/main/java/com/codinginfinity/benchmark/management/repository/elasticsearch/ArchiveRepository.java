package com.codinginfinity.benchmark.management.repository.elasticsearch;

import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository definition for the {@link Archive} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface ArchiveRepository extends ElasticsearchCrudRepository<Archive, String> {

    Optional<Archive> findOneById(String id);
}
