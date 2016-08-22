package com.codinginfinity.benchmark.management.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Represent classifiers for {@link Dataset} repository objects.
 *
 * @see RepoEntity
 * @see Dataset
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@NoArgsConstructor
@Entity
public class DatasetCategory extends Category {

    private static final long serialVersionUID = -7468131891548677770L;

    public DatasetCategory(Long id, String name) {
        super(id, name);
    }
}
