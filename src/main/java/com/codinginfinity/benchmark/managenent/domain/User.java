package com.codinginfinity.benchmark.managenent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class User extends AbstractBaseEntity {

    private static final long serialVersionUID = -1983215733948822003L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[_'.@A-Za-z0-9-]*$")
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 8, max = 60)
    @Column(length = 60, nullable = false)
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @JsonIgnore
    @Size(min = 20, max = 20)
    @Column(length = 20, nullable = true)
    private String activationKey;

    @Size(min = 20, max = 20)
    @Column(length = 20, nullable = true)
    private String resetKey;

    @Past
    @Column(nullable = true)
    private ZonedDateTime resetDate = null;

    @JsonIgnore
    @Transient
    private Set<Authority> authorities = new HashSet<>();

    public void setUsername(String username) {
        this.username = username.toLowerCase(Locale.ENGLISH);
    }
}
