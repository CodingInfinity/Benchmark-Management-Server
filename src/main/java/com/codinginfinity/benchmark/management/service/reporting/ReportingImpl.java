package com.codinginfinity.benchmark.management.service.reporting;

import com.codinginfinity.benchmark.management.domain.Job;
import com.codinginfinity.benchmark.management.domain.Measurement;
import com.codinginfinity.benchmark.management.jackson.mixin.MeasurementFormat;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import com.codinginfinity.benchmark.management.service.reporting.request.DownloadResultsRequest;
import com.codinginfinity.benchmark.management.service.reporting.response.DownloadResultsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * A reference implementation of the {@link Reporting} service contract.
 *
 * @see com.codinginfinity.benchmark.management.service.reporting.exception
 * @see com.codinginfinity.benchmark.management.service.reporting.request
 * @see com.codinginfinity.benchmark.management.service.reporting.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
@Service
public class ReportingImpl implements Reporting {

    @Inject
    private JobRepository jobRepository;

    @Inject
    private JavaTimeModule javaTimeModule;

    @Override
    @Transactional(readOnly = true)
    public DownloadResultsResponse downloadResults(DownloadResultsRequest request) throws ProcessingException, NonExistentException {

        try {
            Job job = jobRepository.findOne(request.getJobId());

            if (job == null)
                throw new NonExistentException("Job doesn't exist");

            CsvMapper mapper = new CsvMapper();
            mapper.registerModule(javaTimeModule);
            CsvSchema schema = CsvSchema.builder()
                    .addColumn("timestamp")
                    .addColumn("value")
                    .build();
            mapper.addMixIn(Measurement.class, MeasurementFormat.class);

            String csv = mapper.writer(schema).writeValueAsString(job.getMeasurements());
            return new DownloadResultsResponse(csv);
        } catch (JsonProcessingException e) {
            throw new ProcessingException("Results could not be converted to CSV file format.");
        }
    }
}
