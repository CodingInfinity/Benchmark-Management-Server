package com.codinginfinity.benchmark.management.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Represent classifiers for {@link Algorithm} repository objects.
 *
 * @see RepoEntity
 * @see Algorithm
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@NoArgsConstructor
@Entity
public class AlgorithmCategory extends Category {

    private static final long serialVersionUID = 5172121759729855063L;

    public AlgorithmCategory(Long id, String name) {
        super(id, name);
    }
}
