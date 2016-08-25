package com.codinginfinity.benchmark.management.service.reporting.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.Data;

/**
 * Created by andrew on 2016/08/25.
 */
@Data
public class DownloadResultsRequest extends Request {

    private static final long serialVersionUID = -6863901248441454301L;

    private final Long jobId;
}
