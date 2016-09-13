package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by reinhardt on 2016/08/11.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateExperimentRequest extends Request{
    Long algorithm;

    Integer timeout;

    Integer probeInterval;

    Long[] datasets;

    Integer[] measurementType;

    Integer quantity;

    Integer languageType;
}
