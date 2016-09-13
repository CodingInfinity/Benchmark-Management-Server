package com.codinginfinity.benchmark.management;

import com.codinginfinity.benchmark.management.config.BenchmarkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by andrew on 2016/06/13.
 */
@SpringBootApplication
@EnableConfigurationProperties({ BenchmarkProperties.class, LiquibaseProperties.class })
@Slf4j
public class ManagementApp {

    private static SpringApplication application = new SpringApplication(ManagementApp.class);

    public static void main(String[] args) throws UnknownHostException {
        Environment environment = application.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"));
    }

    public static void setApplication(SpringApplication application) {
        ManagementApp.application = application;
    }
}
