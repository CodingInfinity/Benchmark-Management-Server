package com.codinginfinity.benchmark.managenent.domain;

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
    private Experiment job;

   /*@NotNull
   private MeasurementType measurementType;*/

    private List<ResultEntry> resultEntries;
}
