package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.*;

/**
 * Created by fabio on 2016/08/26.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExperimentByIdResponse  extends Response{
    Experiment experiment;
}

