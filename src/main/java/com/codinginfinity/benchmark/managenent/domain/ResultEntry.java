package com.codinginfinity.benchmark.managenent.domain;

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
}
