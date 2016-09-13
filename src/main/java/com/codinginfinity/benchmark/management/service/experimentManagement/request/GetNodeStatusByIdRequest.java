package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.Data;

/**
 * Created by andrew on 2016/08/31.
 */
@Data
public class GetNodeStatusByIdRequest extends Request {

    private static final long serialVersionUID = 307909557450789070L;

    private final String id;
}
