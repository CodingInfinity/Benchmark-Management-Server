package com.codinginfinity.benchmark.management.thrift.messages;

import com.codinginfinity.apache.camel.component.thrift.ThriftDataFormat;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/08/31.
 */
public class ThriftHeartbeatMessageDataFormat extends ThriftDataFormat<Heartbeat, Heartbeat._Fields> {

    @Override
    public Heartbeat create() {
        return new Heartbeat();
    }
}