package com.codinginfinity.benchmark.management.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Represents an abstract object which will be stored in the repository system.
 * It is suggested that new objects to be persisted to the repository system
 * extend this object and make use of the provided service interface
 * {@link com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagement},
 * more specifically making use of the provided refernce implementation
 * {@link com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagementImpl}.
 *
 * @author Reinhardt Cromhout
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class RepoEntity<T extends Category> implements Serializable {

    private static final long serialVersionUID = 3368347492285795646L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    /**
     * User provided identifier of the object.
     */
    private String name;

    @OneToOne
    @NotNull
    /**
     * The user who takes responsibility of the object.
     */
    private User user;

    /**
     * User provided description of the object.
     */
    private String description;

    /**
     * Whether object contains documents. In reference implementation it is
     * set to indicate that associated objects have been uploaded to
     * persistence store.
     */
    private boolean documents = false;

    /**
     * Filename of the object.
     */
    private String filename;

    /**
     * Classifiers associated with the object.
     * @return Returns a list of categories.
     */
    public abstract List<T> getCategories();

    /**
     * Adds a classifier to object.
     * @param category Classifier to add to object.
     */
    public abstract void addCategory(T category);
}
