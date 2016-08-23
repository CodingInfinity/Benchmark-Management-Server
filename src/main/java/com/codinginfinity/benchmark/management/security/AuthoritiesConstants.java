package com.codinginfinity.benchmark.management.security;

/**
 * Class that defines the various roles within the benchmark management
 * system.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */
public final class AuthoritiesConstants {

    private AuthoritiesConstants() {
    }

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
