package com.batch.testspringbatch.output.dbDemo;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Configuration
public class DbOutputDemoJobReaderConfiguration {
    @Bean
    public FlatFileItemReader<NewEmployees> dbOutputDemoJobFlatFileReader() {
        FlatFileItemReader<NewEmployees> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("employeesInit.csv"));
        DefaultLineMapper<NewEmployees> employeesDefaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date"});
        employeesDefaultLineMapper.setLineTokenizer(tokenizer);
        employeesDefaultLineMapper.setFieldSetMapper((fieldSet -> NewEmployees.builder()
                .emp_no(fieldSet.readInt("emp_no"))
                .birth_date(fieldSet.readDate("birth_date"))
                .first_name(fieldSet.readString("first_name"))
                .last_name(fieldSet.readString("last_name"))
                .hire_date(fieldSet.readDate("hire_date"))
                .gender(fieldSet.readString("gender"))
                .build()));
        employeesDefaultLineMapper.afterPropertiesSet();
        reader.setLineMapper(employeesDefaultLineMapper);
        return reader;
    }
}
