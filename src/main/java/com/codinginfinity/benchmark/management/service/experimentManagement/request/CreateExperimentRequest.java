package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.User;
import lombok.*;

import java.util.List;

/**
 * Created by reinhardt on 2016/08/11.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateExperimentRequest {
    User user;

    Algorithm algorithm;

    Integer timeout;

    Integer probeInterval;

    List<Dataset> datasets;
}
