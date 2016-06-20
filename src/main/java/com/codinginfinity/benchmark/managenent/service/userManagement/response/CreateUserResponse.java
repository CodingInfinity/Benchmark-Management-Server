package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.service.Response;
import com.codinginfinity.benchmark.managenent.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse extends Response {

    private User user;
}
