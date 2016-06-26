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
public class Algorithm extends RepoEntity {

    private static final long serialVersionUID = 251521771756625319L;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "algorithm_algorithmCategory",
            joinColumns = {@JoinColumn(name = "algorithm_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<DatasetCategory> categories;
}
