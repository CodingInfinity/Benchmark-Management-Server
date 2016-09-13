package com.codinginfinity.benchmark.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Represent classifiers which can be applied to repository objects.
 *
 * @see RepoEntity
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Category implements Serializable {

    private static final long serialVersionUID = -7595019597330939464L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    @Size(max = 50)
    @Column(unique = true)
    /**
     * User provided value for classifier. Classifier must be unique which context of class type.
     */
    protected String name;
}
