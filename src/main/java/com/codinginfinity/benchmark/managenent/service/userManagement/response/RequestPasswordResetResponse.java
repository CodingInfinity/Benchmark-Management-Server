package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@AllArgsConstructor
public class RequestPasswordResetResponse extends Response {

    private static final long serialVersionUID = 7710675299049420857L;

    private Optional<User> user;
}
