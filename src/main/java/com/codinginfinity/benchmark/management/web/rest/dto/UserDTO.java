package com.codinginfinity.benchmark.management.web.rest.dto;

/**
 * Created by andrew on 2016/06/22.
 */

import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    public UserDTO(User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.isActivated(),
                user.getAuthorities().stream().map(Authority::getName)
                        .collect(Collectors.toSet()));
    }

    public UserDTO(String login, String firstName, String lastName,
                   String email, boolean activated, Set<String> authorities) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.authorities = authorities;
    }
}

