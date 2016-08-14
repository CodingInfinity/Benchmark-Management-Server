package com.codinginfinity.benchmark.management.domain;


import com.codinginfinity.benchmark.management.thrift.messages.LanguageType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brenton on 7/28/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Experiment implements Serializable {

    private static final long serialVersionUID = -3448618051037330977L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Column(name = "requested_date", nullable = false)
    private ZonedDateTime requestedDate = ZonedDateTime.now();

    @NotNull
    @Column(name = "timeout", nullable = false)
    private Integer timeout;

    @NotNull
    @Column(name = "probe_interval", nullable = false)
    private Integer probeInterval;

    @NotNull
    @Column(name = "language_type", nullable = false)
    private LanguageType languageType;
}
