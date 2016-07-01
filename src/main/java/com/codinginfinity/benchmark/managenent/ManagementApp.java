package com.codinginfinity.benchmark.managenent;

import com.codinginfinity.benchmark.managenent.config.BenchmarkProperties;
import com.codinginfinity.benchmark.managenent.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew on 2016/06/13.
 */
@SpringBootApplication
@EnableConfigurationProperties({ BenchmarkProperties.class })
@Slf4j
public class ManagementApp {

    @Inject
    private Environment environment;

    @Inject
    private static BenchmarkProperties benchmarkProperties;

    private static SpringApplication application = new SpringApplication(ManagementApp.class);

    public static void main(String[] args) throws UnknownHostException {
        Environment environment = application.run(args).getEnvironment();
        String swagger = benchmarkProperties.getSwagger().getEnabled() ?
                "http://127.0.0.1:" + environment.getProperty("server.port") + "/swagger.html" :
                "Not Enabled";
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! REST API URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Swagger: \t{}----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"));
    }

    public static void setApplication(SpringApplication application) {
        ManagementApp.application = application;
    }

    @PostConstruct
    public void initApplication() {
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
