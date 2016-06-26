package com.codinginfinity.benchmark.managenent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Dataset extends RepoEntity {

    private static final long serialVersionUID = -5132732623123018351L;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "dataset_datasetCategory",
            joinColumns = {@JoinColumn(name = "dataset_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<DatasetCategory> categories;


    public Dataset(Long id, String name, User user, List<DatasetCategory> categories) {
        super(id, name, user);
        this.categories = categories;
    }
}
