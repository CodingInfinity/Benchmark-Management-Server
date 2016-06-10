package com.codinginfinity.benchmark.config;

import com.codinginfinity.benchmark.ManagementApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrew on 2016/06/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ManagementApp.class)
@WebIntegrationTest(randomPort = true)
public class OAuth2Test {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;

    @Test
    public void testCORS() {
        final ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/oauth/token",
                HttpMethod.OPTIONS, null, Void.class);
        final HttpHeaders headers = response.getHeaders();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(headers.getAccessControlAllowOrigin());
    }
}
