package com.codinginfinity.benchmark.managenent.repository.binary;

import com.codinginfinity.benchmark.managenent.domain.binary.Archive;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrew on 2016/07/06.
 */
@Repository
public interface ArchiveRepository extends ElasticsearchCrudRepository<Archive, String> {

}
