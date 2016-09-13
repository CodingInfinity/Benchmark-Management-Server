package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.*;

import java.util.List;

/**
 * Created by fabio on 2016/08/26.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllExperimentsResponse extends Response {
    List<Experiment> experiments;
}
