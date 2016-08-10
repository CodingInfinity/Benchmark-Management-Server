package com.codinginfinity.benchmark.management.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by andrew on 2016/06/25.
 */
@NoArgsConstructor
@Entity
public class AlgorithmCategory extends Category {

    private static final long serialVersionUID = 5172121759729855063L;

    public AlgorithmCategory(Long id, String name) {
        super(id, name);
    }
}
