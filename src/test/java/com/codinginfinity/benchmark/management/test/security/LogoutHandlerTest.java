package com.codinginfinity.benchmark.management.test.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/07/06.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SecuritySpringTest.class)
public class LogoutHandlerTest {

    @Inject
    private TokenStore tokenStore;

    @Inject
    private LogoutSuccessHandler logoutSuccessHandler;


    private HttpServletRequest request;


    private HttpServletResponse response;


    private Authentication authentication;


    private OAuth2AccessToken token;

    private int[] status = {-1};

    @Before
    public void setUp() throws Exception {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        authentication = Mockito.mock(Authentication.class);
        token =  Mockito.mock(OAuth2AccessToken.class);
        Mockito.reset(tokenStore);

        status = new int[]{-1};
        doAnswer(invocationOnMock -> status[0] = (int) invocationOnMock.getArguments()[0]).when(response).setStatus(anyInt());
        doNothing().when(tokenStore).removeAccessToken(anyObject());
    }

    @Test
    public void headerNotPresentTest() throws IOException, ServletException {

        when(request.getHeader(anyString())).thenReturn(null);
        when(tokenStore.readAccessToken(anyString())).thenReturn(token);

        logoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verifyResults(0, 0);
    }

    @Test
    public void headerBasicAuthenticationTest() throws IOException, ServletException {

        when(request.getHeader("Authorization")).thenReturn("Basic username:password");
        when(tokenStore.readAccessToken(anyString())).thenReturn(token);

        logoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verifyResults(0, 0);
    }

    @Test
    public void clearedTokenInAccessStoreTest() throws IOException, ServletException {

        when(request.getHeader("Authorization")).thenReturn("Bearer b3791eb3-fb71-4219-9688-26b2dab98cd4");
        when(tokenStore.readAccessToken(anyString())).thenReturn(null);

        logoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verifyResults(1, 0);
    }

    @Test
    public void removeValidTokenInAccessStoreTest() throws IOException, ServletException {

        when(request.getHeader("Authorization")).thenReturn("Bearer b3791eb3-fb71-4219-9688-26b2dab98cd4");
        when(tokenStore.readAccessToken(anyString())).thenReturn(token);

        logoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verifyResults(1, 1);

    }

    private void verifyResults(int readAccessToken, int removeAccessToken) {
        verify(tokenStore, times(readAccessToken)).readAccessToken(anyString());
        verify(tokenStore, times(removeAccessToken)).removeAccessToken(anyObject());
        assertEquals(HttpServletResponse.SC_OK, status[0]);
    }
}
