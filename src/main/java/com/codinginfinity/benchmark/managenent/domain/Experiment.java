package com.codinginfinity.benchmark.managenent.domain;

/**
 * Created by Brenton on 7/28/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Experiment implements Serializable {

    private static final long serialVersionUID = -3448618051037330977L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    @OneToMany
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Column(name = "requested_date", nullable = false)
    private ZonedDateTime requestedDate;

    @NotNull
    @Column(name = "tiemout", nullable = false)
    private Integer timeout;

    @NotNull
    @Column(name = "probe_interval", nullable = false)
    private Integer probeInterval;
}
