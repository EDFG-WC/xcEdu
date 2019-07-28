package com.batch.testspringbatch.input.restart;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class RestartDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("flatFileDemoWriter")
    private ItemWriter<? super Employees> flatFileDemoWriter;


    @Autowired
    @Qualifier("restartDemoReader")
    private ItemReader<Employees> restartDemoReader;


    @Bean
    public Job multipleFileDemoJob() {
        return jobBuilderFactory.get("restartDemoJob")
                .start(multipleFileDemoStep())
                .build();
    }

    @Bean
    public Step multipleFileDemoStep() {
        return stepBuilderFactory.get("restartDemoStep")
                .<Employees, Employees>chunk(10)
                .reader(restartDemoReader)
                .writer(flatFileDemoWriter) //这里复用
                .build();
    }
}
