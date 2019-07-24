package com.batch.testspringbatch.input.flatFile;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FlatFileDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("flatFileDemoWriter")
    private ItemWriter<? super Employees> flatFileDemoWriter;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job flatFileDemoJob() {
        return jobBuilderFactory.get("flatFileDemoJob")
                .start(flatFileDemoStep())
                .build();
    }

    @Bean
    public Step flatFileDemoStep() {
        return stepBuilderFactory.get("flatFileDemoStep")
                .<Employees, Employees>chunk(100)
                .reader(flatFileItemReader())
                .writer(flatFileDemoWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Employees> flatFileItemReader() {
        FlatFileItemReader<Employees> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("employees.csv"));
        reader.setLinesToSkip(1); //从1开始
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date"});

        DefaultLineMapper<Employees> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper((fieldSet -> {
            return Employees.builder()
                    .empNo(fieldSet.readInt("emp_no"))
                    .birthDate(fieldSet.readDate("birth_date"))
                    .firstName(fieldSet.readString("first_name"))
                    .lastName(fieldSet.readString("last_name"))
                    .hireDate(fieldSet.readDate("hire_date"))
                    .gender(fieldSet.readString("gender"))
                    .build();
        }));
        lineMapper.afterPropertiesSet();
        reader.setLineMapper(lineMapper);
        return reader;
    }
}
