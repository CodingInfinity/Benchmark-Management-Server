package com.codinginfinity.benchmark.management.domain.elasticsearch.archive;

import com.codinginfinity.benchmark.management.service.repositoryManagement.request.AddRepoEntityRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * Defines a file object used to represent a file in an uploaded archive.
 * Object however doesn't contain the file contents.
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
public class ArchiveFile extends ArchiveNode {

    @Id
    private String id;
}
