package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import com.codinginfinity.benchmark.management.service.Response;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeSummary;
import lombok.Data;

import java.util.List;

/**
 * Created by andrew on 2016/08/31.
 */
@Data
public class GetNodesSummaryResponse extends Response {

    private static final long serialVersionUID = -5689986414613264192L;

    private final List<NodeSummary> nodeSummaryList;
}
