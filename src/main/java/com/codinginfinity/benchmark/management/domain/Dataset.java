package com.codinginfinity.benchmark.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the metadata of an user uploaded dataset. The physical file is
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
public class Dataset extends RepoEntity<DatasetCategory> {

    private static final long serialVersionUID = -5132732623123018351L;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dataset_dataset_category",
            joinColumns = {@JoinColumn(name = "dataset_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    /**
     *  Classifiers associated with the object.
     */
    private List<DatasetCategory> categories;

    @Override
    public List<DatasetCategory> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(DatasetCategory category) {
        if(categories == null){
            categories = new ArrayList<DatasetCategory>();
        }
        categories.add(category);
    }
}
