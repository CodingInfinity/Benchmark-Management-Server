package com.codinginfinity.benchmark.management.thrift.messages;

import com.codinginfinity.apache.camel.component.thrift.ThriftDataFormat;
import org.apache.thrift.protocol.TProtocolFactory;

import javax.inject.Inject;

/**
 * Created by reinhardt on 2016/08/10.
 */
public class ThriftJobSpecificationMessageDataFormat extends ThriftDataFormat<JobSpecificationMessage, JobSpecificationMessage._Fields> {

    @Inject
    private TProtocolFactory factory;

    @Override
    public JobSpecificationMessage create() {
        return new JobSpecificationMessage();
    }
}
