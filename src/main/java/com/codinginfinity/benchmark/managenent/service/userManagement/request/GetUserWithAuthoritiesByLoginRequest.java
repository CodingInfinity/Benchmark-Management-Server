package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class GetUserWithAuthoritiesByLoginRequest extends Request {

    private static final long serialVersionUID = -2524907316059103306L;

    private String username;
}
