package com.codinginfinity.benchmark.management.jackson.mixin;

import com.codinginfinity.benchmark.management.domain.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZonedDateTime;

/**
 * Created by andrew on 2016/08/25.
 */
public abstract class MeasurementFormat {

    @JsonIgnore
    abstract Long getId();

    abstract ZonedDateTime getTimestamp();

    abstract Double getValue();

    @JsonIgnore
    abstract Job getJob();
}
