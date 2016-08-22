package com.codinginfinity.benchmark.management.domain.elasticsearch.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a directory object used to represent directories in user uploaded
 * archives.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Directory extends INode {

    /**
     * List of {@code INode} objects which can represents files or other
     * directories.
     */
    private List<INode> nodeList = new ArrayList<>();
}
