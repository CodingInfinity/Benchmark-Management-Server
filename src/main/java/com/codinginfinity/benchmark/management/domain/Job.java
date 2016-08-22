package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.thrift.messages.MeasurementType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a single job specification and history.
 *
 * @see com.codinginfinity.benchmark.management.domain.Job
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@Entity
public class Job implements Serializable {

    private static final long serialVersionUID = 3479479380288781959L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    /**
     * The experiment with which this job is associated with.
     */
    private Experiment experiment;

    @NotNull
    /**
     * The type of measurement requested by the user.
     */
    private MeasurementType measurementType;

    @OneToMany(cascade = CascadeType.ALL)
    /**
     * List of measurements made by the monitor applications.
     */
    private List<Measurement> measurements;

    @NotNull
    @OneToOne
    /**
     * The algorithm upon which benchmarking was done.
     */
    private Algorithm algorithm;

    @NotNull
    @OneToOne
    /**
     * The associated dataset supplied to the algorithm.
     */
    private Dataset dataset;
}
