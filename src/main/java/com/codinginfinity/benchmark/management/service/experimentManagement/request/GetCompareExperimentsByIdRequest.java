package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by fabio on 2016/08/26.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCompareExperimentsByIdRequest extends Request {
    Long id;
}
