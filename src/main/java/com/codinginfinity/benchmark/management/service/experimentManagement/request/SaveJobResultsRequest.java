package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.thrift.messages.ResultMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by reinhardt on 2016/08/09.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveJobResultsRequest {
    ResultMessage resultMessage;
}
