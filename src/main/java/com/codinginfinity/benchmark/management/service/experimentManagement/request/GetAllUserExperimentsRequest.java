package com.codinginfinity.benchmark.management.service.experimentManagement.request;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by fabio on 2016/08/26.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllUserExperimentsRequest extends Request{
    User user;
}
