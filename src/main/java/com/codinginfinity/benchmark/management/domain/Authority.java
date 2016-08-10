package com.codinginfinity.benchmark.management.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Authority implements Serializable {

    private static final long serialVersionUID = -5809085928075081335L;

    @NotNull
    @Size(min = 1, max = 50)
    @Id
    @Column(length = 50)
    private String name;
}
