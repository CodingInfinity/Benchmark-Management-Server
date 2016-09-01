package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import lombok.Data;

/**
 * Created by andrew on 2016/08/31.
 */
@Data
public class RegisterNodeHeartbeatRequest extends Request {

    private static final long serialVersionUID = 7027657934430202712L;

    private final Heartbeat heartbeat;
}
