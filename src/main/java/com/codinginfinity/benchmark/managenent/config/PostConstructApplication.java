package com.codinginfinity.benchmark.managenent.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by andrew on 2016/07/01.
 */
@Configuration
@Slf4j
public class PostConstructApplication {

    @Inject
    private Environment environment;

    @PostConstruct
    public void initApplication() throws UnknownHostException {
        if (environment.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default profile: {}", Constants.SPRING_PROFILE_DEVELOPMENT);
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(environment.getActiveProfiles()));
            Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
                log.error("You have misconfigured your application! It should not run " +
                        "with both the 'dev' and 'prod' profiles at the same time.");
            }
        }
    }
}
