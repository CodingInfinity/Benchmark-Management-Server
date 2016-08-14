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
public class Algorithm extends RepoEntity<AlgorithmCategory> {

    private static final long serialVersionUID = 251521771756625319L;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "algorithm_algorithm_category",
            joinColumns = {@JoinColumn(name = "algorithm_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
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