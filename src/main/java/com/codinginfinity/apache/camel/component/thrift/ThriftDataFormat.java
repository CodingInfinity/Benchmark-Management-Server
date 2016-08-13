package com.codinginfinity.apache.camel.component.thrift;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by andrew on 2016/07/05.
 */
public abstract class ThriftDataFormat<T extends TBase<?,?>, F extends TFieldIdEnum> implements DataFormat {

    @Inject
    private TProtocolFactory factory;

    @Override
    public void marshal(Exchange exchange, Object o, OutputStream outputStream) throws Exception {
        TTransport transport = new TIOStreamTransport(outputStream);
        TProtocol protocol = factory.getProtocol(transport);
        ((T) o).write(protocol);
    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream inputStream) throws Exception {
        T helper = create();
        TTransport transport = new TIOStreamTransport(inputStream);
        TProtocol protocol = factory.getProtocol(transport);
        try {
            helper.read(protocol);
        } catch (TException e) {
            throw new IOException(e);
        }
        return helper;
    }

    public abstract T create();
}
