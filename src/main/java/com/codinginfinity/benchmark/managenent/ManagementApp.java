package com.codinginfinity.benchmark.managenent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.net.UnknownHostException;

/**
 * Created by andrew on 2016/06/13.
 */
@SpringBootApplication
public class ManagementApp {

    private static SpringApplication application = new SpringApplication(ManagementApp.class);

    public static void main(String[] args) {
        application.run(args);
    }

    public static void setApplication(SpringApplication application) {
        ManagementApp.application = application;
    }
}
