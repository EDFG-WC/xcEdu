package com.batch.testspringbatch.output.dbDemo;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbOutputDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("dbOutputDemoJobFlatFileReader")
    public ItemReader<NewEmployees> dbOutputDemoJobFlatFileReader;

    @Autowired
    @Qualifier("dbOutputDemoJobFlatFileWriter")
    public ItemWriter<NewEmployees> dbOutputDemoJobFlatFileWriter;

    @Bean
    public Job dbOutputDemoJob() {
        return jobBuilderFactory.get("dbOutputDemoJob")
                .start(dbOutputDemoStep())
                .build();
    }

    @Bean
    public Step dbOutputDemoStep() {
        return stepBuilderFactory.get("dbOutputDemoStep")
                .<NewEmployees, NewEmployees>chunk(10)
                .reader(dbOutputDemoJobFlatFileReader)
                .writer(dbOutputDemoJobFlatFileWriter)
                .build();
    }
}
