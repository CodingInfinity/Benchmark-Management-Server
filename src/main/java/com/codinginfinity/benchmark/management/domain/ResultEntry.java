package com.codinginfinity.benchmark.management.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
/**
 * Created by Brenton on 7/28/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ResultEntry implements Serializable {

    private static final long serialVersionUID = 8844214395918274514L;

    @Id
    private Long id;

    @NotNull
    private ZonedDateTime timestamp;

    @NotNull
    private Double value;

    @NotNull
    @OneToOne
    private Job job;
}
