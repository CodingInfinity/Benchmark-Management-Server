package com.codinginfinity.benchmark.management.service.userManagement.response;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class DeleteUserResponse extends Response {

    private static final long serialVersionUID = 8387993882678228322L;

    private User user;
}
