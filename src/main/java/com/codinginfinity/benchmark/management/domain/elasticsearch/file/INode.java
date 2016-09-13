package com.codinginfinity.benchmark.management.domain.elasticsearch.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Defines the *nix equivalent of an iNode. Files persisted to backed will be
 * derived from this object.
 *
 * @see com.codinginfinity.benchmark.management.domain.elasticsearch.file.Directory
 * @see com.codinginfinity.benchmark.management.domain.elasticsearch.file.File
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class INode {

    /**
     * Represents the name of the file or directory.
     */
    private String name;
}
