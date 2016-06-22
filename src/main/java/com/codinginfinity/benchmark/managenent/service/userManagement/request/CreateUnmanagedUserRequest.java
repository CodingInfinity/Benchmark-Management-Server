package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUnmanagedUserRequest extends Request {

    private static final long serialVersionUID = 4121774475207109238L;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}
