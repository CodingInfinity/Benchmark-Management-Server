package com.codinginfinity.benchmark.management.domain.elasticsearch.archive;

import com.codinginfinity.benchmark.management.service.repositoryManagement.request.AddRepoEntityRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * Defines the root of an archive object. Used to save the internal
 * representation of an archive to Elasticsearch to allow easy retrieval of
 * internal structure. Used by front end to assist in providing the file
 * browser interface to allow client to parse uploaded archive.
 *
 * @see com.codinginfinity.benchmark.management.repository.elasticsearch
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement#addRepoEntity(AddRepoEntityRequest)
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagementImpl#addRepoEntity(AddRepoEntityRequest)
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "archive", type="archive")
public class Archive extends ArchiveNode {

    @Id
    private String id;
}
