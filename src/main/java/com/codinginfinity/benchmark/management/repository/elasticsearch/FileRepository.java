package com.codinginfinity.benchmark.management.repository.elasticsearch;

import com.codinginfinity.benchmark.management.domain.elasticsearch.file.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository definition for the {@link File} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.elasticsearch.file.File
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface FileRepository extends ElasticsearchCrudRepository<File, String> {
}
