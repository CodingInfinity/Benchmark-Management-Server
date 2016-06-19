package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestPasswordResetRequest extends Request {

    private static final long serialVersionUID = -8338145629464497317L;

    private String email;
}
