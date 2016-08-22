package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.thrift.messages.LanguageType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request made by a user to benchmark an algorithm, with supplied,
 * dataset and associated parameters such as measurement type, language, timeout,
 * and probe interval.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@Entity
public class Experiment implements Serializable {

    private static final long serialVersionUID = -3448618051037330977L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    /**
     * The user who requested the experiment.
     */
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    /**
     * List of all job specifications.
     */
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Column(name = "requested_date", nullable = false)
    /**
     * Date when request was sent to benchmarking system.
     */
    private ZonedDateTime requestedDate = ZonedDateTime.now();

    @NotNull
    @Column(name = "timeout", nullable = false)
    /**
     * Parameter to specify when backend node should terminate benchmarking.
     */
    private Integer timeout;

    @NotNull
    @Column(name = "probe_interval", nullable = false)
    /**
     * Parameter to specify how frequently measurements should be made on the
     * monitor.
     */
    private Integer probeInterval;

    @NotNull
    @Column(name = "language_type", nullable = false)
    /**
     * The language of the algorithm.
     */
    private LanguageType languageType;
}
