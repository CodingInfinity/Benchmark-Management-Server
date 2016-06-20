package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class UpdateUserRequest extends Request {

    private static final long serialVersionUID = -3412424054853591807L;

    private String firstName;

    private String lastName;

    private String email;
}
