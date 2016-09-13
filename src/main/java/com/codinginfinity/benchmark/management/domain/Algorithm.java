package com.codinginfinity.benchmark.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the metadata of an user uploaded algorithm. The physical file is
 * stored separately using a {@link com.codinginfinity.benchmark.management.domain.elasticsearch.file.File}
 * object.
 *
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @author Fabio Loreggian
 * @version 1.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Algorithm extends RepoEntity<AlgorithmCategory> {

    private static final long serialVersionUID = 251521771756625319L;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "algorithm_algorithm_category",
            joinColumns = {@JoinColumn(name = "algorithm_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    /**
     * Classifiers associated with the object.
     */
    private List<AlgorithmCategory> categories;

    @Override
    public List<AlgorithmCategory> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(AlgorithmCategory category) {
        if(categories == null){
            categories = new ArrayList<>();
        }
        categories.add(category);
    }
}
