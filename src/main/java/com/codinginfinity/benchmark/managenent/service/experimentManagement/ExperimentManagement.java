package com.codinginfinity.benchmark.managenent.service.experimentManagement;

import com.codinginfinity.benchmark.managenent.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.managenent.service.experimentManagement.respones.SaveJobResultsRespones;

/**
 * Created by reinhardt on 2016/08/09.
 */
public interface ExperimentManagement {

    public SaveJobResultsRespones saveJobResults(SaveJobResultsRequest request);
}
