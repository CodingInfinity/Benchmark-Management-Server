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

/**
 * Defines a class to handle Thrift message conversion in the Apache Camel
 * framework by implementing.the required {@link import org.apache.camel.spi.DataFormat}
 * service provider interface.
 *
 * @param <T> Ato generated Thrift Java class object which will be associated
 *           with extended class.
 *
 * @see com.codinginfinity.benchmark.management.config.MessagingConfiguration
 * @see com.codinginfinity.benchmark.management.config.RouterConfiguration
 * @see com.codinginfinity.benchmark.management.thrift
 *
 * @author Andrew Broekman
 * @version 1.0.0
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
        /**
         * Template design pattern is used, as we can't create a new type of T
         * dynamically because of type erasure, call the create method on class
         * extending this class, to return a new and clean appropriate Thrift
         * object which we can populate with dematerialized data.
         */
        T helper = create();
        //TODO Solve actual problem on c++ qpid

            String inputString = IOUtils.toString(inputStream);
            int index = inputString.indexOf("{\"");
            inputString = inputString.substring(index);
            inputStream = new ByteArrayInputStream(inputString.getBytes());


        TTransport transport = new TIOStreamTransport(inputStream);
        TProtocol protocol = factory.getProtocol(transport);
        try {
            helper.read(protocol);
        } catch (TException e) {
            throw new IOException(e);
        }
        return helper;
    }

    /**
     * Create a new Thift object associated with this class.
     * @return New and clean Thrift object associated with this class.
     */
    public abstract T create();
}
