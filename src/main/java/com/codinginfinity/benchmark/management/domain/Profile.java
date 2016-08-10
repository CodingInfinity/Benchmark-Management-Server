package com.codinginfinity.benchmark.management.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andrew on 2016/06/21.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Profile {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    private Set<String> authorities;

    public Profile() {
    }

    public Profile(User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.isActivated(),
                user.getAuthorities().stream().map(Authority::getName)
                        .collect(Collectors.toSet()));
    }
}