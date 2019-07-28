package com.batch.testspringbatch.output.overview;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class OutputOverviewApp {

    public static void main(String[] args) {
        SpringApplication.run(OutputOverviewApp.class, args);
    }

}
