package com.codinginfinity.benchmark.management.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Represents a single measurement made by the backend on a job.
 *
 * @see com.codinginfinity.benchmark.management.domain.Job
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@Entity
public class Measurement implements Serializable {

    private static final long serialVersionUID = 8844214395918274514L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    /**
     * Date and time at which the measurement was made.
     */
    private ZonedDateTime timestamp;

    @NotNull
    /**
     * The probe/measured value.
     */
    private Double value;

    @NotNull
    @OneToOne
    /**
     * The job on which this measurement was made.
     */
    private Job job;
}
