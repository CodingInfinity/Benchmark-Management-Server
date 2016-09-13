package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by reinhardt on 2016/08/09.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveJobResultsResponse {
    private long jobId;
    private long experimentId;
}
