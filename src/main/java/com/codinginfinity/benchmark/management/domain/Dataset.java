package com.codinginfinity.benchmark.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class Dataset extends RepoEntity<DatasetCategory> {

    private static final long serialVersionUID = -5132732623123018351L;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "dataset_dataset_category",
            joinColumns = {@JoinColumn(name = "dataset_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
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
