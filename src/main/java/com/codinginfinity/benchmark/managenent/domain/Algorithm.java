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
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 251521771756625319L;

    @NotNull
    private String name;

    @NotNull
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "algorithm_category",
            joinColumns = {@JoinColumn(name = "algorithm_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<DatasetCategory> categories;
}
