package com.codinginfinity.benchmark.management.service.reporting.response;

import com.codinginfinity.benchmark.management.service.Response;
import lombok.Data;

/**
 * Created by andrew on 2016/08/25.
 */
@Data
public class DownloadResultsResponse extends Response {

    private static final long serialVersionUID = 9082389335912617496L;

    private final String results;
}
