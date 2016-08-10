package com.codinginfinity.benchmark.management.thrift;

import com.codinginfinity.apache.camel.component.thrift.ThriftDataFormat;
import org.apache.thrift.protocol.TProtocolFactory;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/07/05.
 */
public class ThriftMessageDataFormat extends ThriftDataFormat<Message, Message._Fields> {

    @Inject
    private TProtocolFactory factory;

    @Override
    public Message create() {
        return new Message();
    }
}
