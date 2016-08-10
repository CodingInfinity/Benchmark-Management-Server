package com.codinginfinity.benchmark.management.service.userManagement.response;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Response;
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
