package com.batch.testspringbatch.jobParametersDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JobParameterApp {

    public static void main(String[] args) {
        SpringApplication.run(JobParameterApp.class, args);
    }

}
