package com.codinginfinity.benchmark.management.service.experimentManagement.response;

import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by fabio on 2016/08/27.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsJobOnQueueResponse extends Response{
    boolean onQueue;
}
