package com.codinginfinity.benchmark.management.domain.elasticsearch.archive;

import com.codinginfinity.benchmark.management.service.repositoryManagement.request.AddRepoEntityRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a node object which is an equivalent of an INode object in Unix.
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
public class ArchiveNode {

    /**
     * Defines the name of the entry in the archive.
     */
    private String name;

    /**
     * If entry represents a directory, then entity can contain more
     * {@code ArchiveNode} objects.
     */
    private List<ArchiveNode> nodeList = new ArrayList<>();
}
