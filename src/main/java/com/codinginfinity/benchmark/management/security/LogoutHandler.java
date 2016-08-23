package com.codinginfinity.benchmark.management.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout handler to handle oAuth2 AJAX requests for logout. This
 * handler removes tokens from the persistence store to ensure if token is
 * compromised after user is finished, it is rendered useless.
 *
 * Class is injected into the {@link com.codinginfinity.benchmark.management.config.SecurityConfiguration}
 * configuration class and used by the
 * {@link com.codinginfinity.benchmark.management.config.SecurityConfiguration#configure(HttpSecurity)} method.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Component
public class LogoutHandler implements LogoutSuccessHandler {

    public static final String BEARER_AUTHENTICATION = "Bearer ";

    @Inject
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        /* Extract the Authorization header from the HTTP request */
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        /**
         * If token is not null, we have an authorization header. We also need
         * to ensure this authorization value is an oAuth bearer token.
         */
        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
            final OAuth2AccessToken accessToken = tokenStore.readAccessToken(StringUtils.substringAfter(token, BEARER_AUTHENTICATION));

            /**
             * If token is not null, then we have a valid token that needs to
             * be removed from the store.
             */
            if (accessToken != null) {
                tokenStore.removeAccessToken(accessToken);
            }
        }

        /**
         * Return 200 Success code no matter the internal state (if token
         * exists or not, whether header is correct or not), as we don't want
         * leak any security configuration.
         */
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
