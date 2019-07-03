package com.batch.testspringbatch.listenerDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableBatchProcessing
public class ListenerDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(ListenerDemoApp.class, args);
    }

}
