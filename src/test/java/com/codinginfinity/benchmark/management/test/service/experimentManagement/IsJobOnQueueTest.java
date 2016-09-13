package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.domain.Measurement;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.IsJobOnQueueRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.IsJobOnQueueResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by andrew on 2016/08/31. */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class IsJobOnQueueTest {

    @Inject
    private ExperimentRepository experimentRepository;

    @Inject
    private ExperimentManagement experimentManagement;

    @Inject
    private JobRepository jobRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isJobOnQueueNonExistentRepoEntityException() throws NonExistentRepoEntityException {
        Mockito.when(jobRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.empty());
        thrown.expect(NonExistentRepoEntityException.class);
        experimentManagement.isJobOnQueue(new IsJobOnQueueRequest(1L));
    }

    @Test
    public void isJobOnQueueNullMeasurements() throws NonExistentRepoEntityException {
        Mockito.when(jobRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.of(jobNullMeasurements()));

        IsJobOnQueueResponse response = experimentManagement.isJobOnQueue(new IsJobOnQueueRequest(1L));
        Assert.assertTrue(response.isOnQueue());
    }

    @Test
    public void isJobOnQueueEmptyList() throws NonExistentRepoEntityException {
        Mockito.when(jobRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.of(jobEmptyMeasurements()));

        IsJobOnQueueResponse response = experimentManagement.isJobOnQueue(new IsJobOnQueueRequest(1L));
        Assert.assertTrue(response.isOnQueue());
    }

    @Test
    public void isJobOnQueue() throws NonExistentRepoEntityException {
        Mockito.when(jobRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.of(jobMeasurements()));

        IsJobOnQueueResponse response = experimentManagement.isJobOnQueue(new IsJobOnQueueRequest(1L));
        Assert.assertFalse(response.isOnQueue());
    }

    private Job jobNullMeasurements() {
        Job job = new Job();
        job.setId(1L);
        job.setMeasurements(null);
        return job;
    }

    private Job jobEmptyMeasurements() {
        Job job = new Job();
        job.setId(1L);
        job.setMeasurements(new ArrayList<>());
        return job;
    }

    private Job jobMeasurements() {
        Job job = new Job();
        job.setId(1L);

        Measurement measurementInitial = new Measurement();
        measurementInitial.setJob(job);
        measurementInitial.setValue(0.0);
        measurementInitial.setTimestamp(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("GMT")));

        Measurement measurementFinal = new Measurement();
        measurementInitial.setJob(job);
        measurementInitial.setValue(100.0);
        measurementInitial.setTimestamp(ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.of("GMT")));

        ArrayList<Measurement> measurements = new ArrayList<>();
        measurements.add(measurementInitial);
        measurements.add(measurementFinal);

        job.setMeasurements(measurements);
        return job;
    }
}
