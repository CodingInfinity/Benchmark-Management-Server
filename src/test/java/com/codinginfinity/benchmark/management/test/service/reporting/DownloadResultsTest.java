package com.codinginfinity.benchmark.management.test.service.reporting;

import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.domain.Measurement;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.reporting.Reporting;
import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import com.codinginfinity.benchmark.management.service.reporting.request.DownloadResultsRequest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Created by andrew on 2016/08/30.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ReportingConfiguration.class)
public class DownloadResultsTest {

    @Inject
    private JobRepository jobRepository;

    @Inject
    private Reporting reporting;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void downloadResultsNonExistentExceptionTest() throws NonExistentException, ProcessingException {
        Mockito.when(jobRepository.findOne(Mockito.anyLong())).thenReturn(null);
        thrown.expect(NonExistentException.class);
        reporting.downloadResults(new DownloadResultsRequest(8L));
    }

    @Test
    public void downloadResultsTest() throws ProcessingException, NonExistentException {
        Mockito.when(jobRepository.findOne(Mockito.anyLong())).thenReturn(getJob());
        String results = reporting.downloadResults(new DownloadResultsRequest(2L)).getResults();

        StringBuilder expected = new StringBuilder();
        expected.append("1970-01-01T00:00:00.000Z,0.0\n");
        expected.append("1970-01-01T00:00:01.000Z,100.0\n");

        Assert.assertEquals(expected.toString(), results);
    }

    private Job getJob() {
        Job job = new Job();
        job.setMeasurements(new ArrayList<>());
        job.getMeasurements().add(measurementOne());
        job.getMeasurements().add(measurementTwo());
        return job;
    }

    private Measurement measurementOne() {
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setTimestamp(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("GMT")));
        measurement.setValue(0.0);
        return measurement;
    }

    private Measurement measurementTwo() {
        Measurement measurement = new Measurement();
        measurement.setId(2L);
        measurement.setTimestamp(ZonedDateTime.ofInstant(Instant.ofEpochSecond(1) , ZoneId.of("GMT")));
        measurement.setValue(100.0);
        return measurement;
    }
}
