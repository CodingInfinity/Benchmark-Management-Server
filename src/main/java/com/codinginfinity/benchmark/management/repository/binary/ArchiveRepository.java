package com.codinginfinity.benchmark.management.repository.binary;

import com.codinginfinity.benchmark.management.domain.binary.Archive;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrew on 2016/07/06.
 */
@Repository
public interface ArchiveRepository extends ElasticsearchCrudRepository<Archive, String> {

}
