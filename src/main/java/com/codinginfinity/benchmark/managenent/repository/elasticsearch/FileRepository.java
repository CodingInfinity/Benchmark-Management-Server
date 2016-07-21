package com.codinginfinity.benchmark.managenent.repository.elasticsearch;

import com.codinginfinity.benchmark.managenent.domain.elasticsearch.file.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrew on 2016/07/06.
 */
@Repository
public interface FileRepository extends ElasticsearchCrudRepository<File, String> {
}