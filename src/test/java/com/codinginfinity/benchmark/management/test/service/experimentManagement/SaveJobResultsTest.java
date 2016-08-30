package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.SaveJobResultsRequest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/08/30.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class SaveJobResultsTest {

    @Inject
    private ExperimentRepository experimentRepository;

    @Inject
    private ExperimentManagement experimentManagement;

    @Inject
    private JobRepository jobRepository;

    @Ignore
    public void saveJobResults() {
        Mockito.when(jobRepository.findOne(1L)).thenReturn(job());
        Mockito.when(jobRepository.save(Mockito.any(Job.class))).then(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Job job = (Job)invocationOnMock.getArguments()[0];
                Assert.assertEquals(2, job.getMeasurements().size());

                com.codinginfinity.benchmark.management.domain.Measurement firstMeasurement = job.getMeasurements().get(0);
                com.codinginfinity.benchmark.management.domain.Measurement secondMeasurement = job.getMeasurements().get(1);

                Assert.assertEquals(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("GMT")), firstMeasurement.getTimestamp());
                Assert.assertEquals(new Double(0.0), firstMeasurement.getValue());

                Assert.assertEquals(ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.of("GMT")), firstMeasurement.getTimestamp());
                Assert.assertEquals(new Double(100.0), secondMeasurement.getValue());
                return true;
            }
        });
        experimentManagement.saveJobResults(new SaveJobResultsRequest(resultMessage()));
    }

    private Job job() {
        Job job = new Job();
        job.setId(1L);
        return job;
    }

    private  com.codinginfinity.benchmark.management.thrift.messages.ResultMessage resultMessage() {
        com.codinginfinity.benchmark.management.thrift.messages.ResultMessage resultMessage =
                new  com.codinginfinity.benchmark.management.thrift.messages.ResultMessage();
        resultMessage.setJobId(1);
        resultMessage.setMeasurements(getMeasurements());
        return resultMessage;
    }

    private List<com.codinginfinity.benchmark.management.thrift.messages.Measurement> getMeasurements() {
        List<com.codinginfinity.benchmark.management.thrift.messages.Measurement> measurements =
                new ArrayList<>();
        measurements.add(new com.codinginfinity.benchmark.management.thrift.messages.Measurement(0, 0));
        measurements.add(new com.codinginfinity.benchmark.management.thrift.messages.Measurement(1, 100));
        return measurements;
    }
}
