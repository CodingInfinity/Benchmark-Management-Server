package com.codinginfinity.benchmark.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Defines a role object used to represent a roles/permissions of a user.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority implements Serializable {

    private static final long serialVersionUID = -5809085928075081335L;

    @NotNull
    @Size(min = 1, max = 50)
    @Id
    @Column(length = 50, unique = true)
    /**
     * Name of the role/permission
     * Note: Name is the {@link javax.persistence.Id} of the object and must be unique.
     */
    private String name;
}
