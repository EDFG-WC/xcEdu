package com.batch.testspringbatch.nestedJob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class NestedJobsApp {

    public static void main(String[] args) {
        SpringApplication.run(NestedJobsApp.class, args);
    }

}
