package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.thrift.messages.MeasurementType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Brenton on 7/28/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Job implements Serializable {

    private static final long serialVersionUID = 3479479380288781959L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private Experiment experiment;

   @NotNull
   private MeasurementType measurementType;

    @OneToMany
    private List<ResultEntry> resultEntries;

    @NotNull
    private Algorithm algorithm;

    private Dataset dataset;
}
