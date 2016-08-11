package com.codinginfinity.benchmark.management.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.experimentManagement.request.CreateExperimentRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.CreateExperimentResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.SaveJobResultsResponse;

/**
 * Created by reinhardt on 2016/08/09.
 */
public interface ExperimentManagement {

    public SaveJobResultsResponse saveJobResults(SaveJobResultsRequest request);

    public CreateExperimentResponse createExperiment(CreateExperimentRequest request);
}
