package com.codinginfinity.benchmark.management.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by reinhardt on 2016/06/26.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class RepoEntity<T extends Category> implements Serializable {

    private static final long serialVersionUID = 3368347492285795646L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    @NotNull
    private User user;

    private String description;

    private boolean documents = false;

    private String filename;

    public abstract List<T> getCategories();

    public abstract void addCategory(T category);
}
