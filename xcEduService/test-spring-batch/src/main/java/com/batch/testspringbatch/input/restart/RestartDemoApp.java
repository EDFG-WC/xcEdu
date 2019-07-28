package com.batch.testspringbatch.input.restart;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class RestartDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(RestartDemoApp.class, args);
    }

}
