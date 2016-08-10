package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsRespones;

/**
 * Created by reinhardt on 2016/08/09.
 */
public interface ExperimentManagement {

    public SaveJobResultsRespones saveJobResults(SaveJobResultsRequest request);
}
