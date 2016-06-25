package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class ActivateRegistrationResponse extends Response {

    private static final long serialVersionUID = 2570342508250264670L;

    private User user;
}
