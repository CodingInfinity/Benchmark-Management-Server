package com.codinginfinity.benchmark.management.service.userManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestPasswordResetRequest extends Request {

    private static final long serialVersionUID = -8338145629464497317L;

    private String email;
}
