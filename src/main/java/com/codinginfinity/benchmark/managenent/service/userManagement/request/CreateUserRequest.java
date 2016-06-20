package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest extends Request {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Optional<Set<String>> authorities;
}
