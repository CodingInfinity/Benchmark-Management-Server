package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Created by andrew on 2016/06/21.
 */
@Getter
@AllArgsConstructor
public class CreateManagedUserRequest extends Request {

    private static final long serialVersionUID = 9022522238854779698L;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> authorities;
}