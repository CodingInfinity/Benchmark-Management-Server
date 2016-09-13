package com.codinginfinity.benchmark.management.thrift.messages;

import com.codinginfinity.apache.camel.component.thrift.ThriftDataFormat;
import org.apache.thrift.protocol.TProtocolFactory;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/08/10.
 */
public class ThriftResultMessageDataFormat extends ThriftDataFormat<ResultMessage, ResultMessage._Fields> {

    @Inject
    private TProtocolFactory factory;

    @Override
    public ResultMessage create() {
        return new ResultMessage();
    }
}
