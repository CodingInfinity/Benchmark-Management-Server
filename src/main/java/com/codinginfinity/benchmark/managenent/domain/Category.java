package com.codinginfinity.benchmark.managenent.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class Category implements Serializable {

    private static final long serialVersionUID = -7595019597330939464L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    @Size(max = 50)
    @Column(unique = true)
    protected String name;
}
