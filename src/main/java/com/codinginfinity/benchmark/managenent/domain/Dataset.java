package com.codinginfinity.benchmark.managenent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Dataset implements Serializable {

    private static final long serialVersionUID = -5132732623123018351L;

    @NotNull
    private String name;

    @NotNull
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "dataset_category",
            joinColumns = {@JoinColumn(name = "dataset_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<DatasetCategory> categories;
}
