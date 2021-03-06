package com.codinginfinity.benchmark.management.service.userManagement.response;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/21.
 */
@Getter
@AllArgsConstructor
public class CreateManagedUserResponse extends Response {

    private static final long serialVersionUID = -3715470436975966352L;

    private User user;
}
