package com.codinginfinity.benchmark.managenent.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by andrew on 2016/06/25.
 */
@NoArgsConstructor
@Entity
public class DatasetCategory extends Category {

    private static final long serialVersionUID = -7468131891548677770L;

    public DatasetCategory(Long id, String name) {
        super(id, name);
    }
}
