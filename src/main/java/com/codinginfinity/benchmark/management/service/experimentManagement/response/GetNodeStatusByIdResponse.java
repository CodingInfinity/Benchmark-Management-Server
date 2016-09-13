package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import com.codinginfinity.benchmark.management.service.Response;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.Node;
import lombok.Data;

/**
 * Created by andrew on 2016/08/31.
 */
@Data
public class GetNodeStatusByIdResponse extends Response {

    private static final long serialVersionUID = 2140262531825534201L;

    private final Node node;
}
