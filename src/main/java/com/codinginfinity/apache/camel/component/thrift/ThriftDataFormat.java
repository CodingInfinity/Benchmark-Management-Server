package com.codinginfinity.apache.camel.component.thrift;

import com.codinginfinity.benchmark.management.thrift.messages.ResultMessage;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
        //TODO Solve actual problem on c++ qpid
        if(helper instanceof ResultMessage){
            String inputString = IOUtils.toString(inputStream);
            int index = inputString.indexOf("{\"");
            inputString = inputString.substring(index);
            inputStream = new ByteArrayInputStream(inputString.getBytes());
        }

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
