package com.batch.testspringbatch.output.flatFile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class FlatFileJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("flatFileDemoJdbcPagingReader")
    private ItemReader<NewEmployees> flatFileDemoJdbcPagingReader;

    @Autowired
    @Qualifier("flatFileDemoJdbcPagingWriter")
    private ItemWriter<NewEmployees> flatFileDemoJdbcPagingWriter;


    @Bean
    public Job flatFileOutputDemoJob() {
        return jobBuilderFactory.get("flatFileOutputDemoJob")
                .start(flatFileOutputDemoStep()).build();
    }

    @Bean
    public Step flatFileOutputDemoStep() {
        return stepBuilderFactory.get("flatFileOutputDemoStep")
                .<NewEmployees, NewEmployees>chunk(10)
                .reader(flatFileDemoJdbcPagingReader)
                .writer(flatFileDemoJdbcPagingWriter)
                .build();
    }
}
