package com.batch.testspringbatch.output.flatFile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FlatFileDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(FlatFileDemoApp.class, args);
    }

}
