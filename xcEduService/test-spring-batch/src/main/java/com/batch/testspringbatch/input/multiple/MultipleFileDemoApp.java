package com.batch.testspringbatch.input.multiple;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MultipleFileDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(MultipleFileDemoApp.class, args);
    }

}
