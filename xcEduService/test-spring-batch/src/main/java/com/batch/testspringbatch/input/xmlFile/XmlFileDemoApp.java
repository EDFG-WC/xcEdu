package com.batch.testspringbatch.input.xmlFile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class XmlFileDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(XmlFileDemoApp.class, args);
    }

}
