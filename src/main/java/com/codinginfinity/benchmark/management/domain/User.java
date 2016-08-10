package com.codinginfinity.benchmark.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class User implements Serializable{

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
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
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
    @Column(name = "activation_key", length = 20, nullable = true)
    private String activationKey;

    @Size(min = 20, max = 20)
    @Column(name = "reset_key", length = 20, nullable = true)
    private String resetKey;

    @Past
    @Column(name = "reset_date", nullable = true)
    private ZonedDateTime resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    public void setUsername(String username) {
        this.username = username.toLowerCase(Locale.ENGLISH);
    }
}
