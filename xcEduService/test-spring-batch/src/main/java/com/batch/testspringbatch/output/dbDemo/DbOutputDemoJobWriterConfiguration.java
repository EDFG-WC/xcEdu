package com.batch.testspringbatch.output.dbDemo;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbOutputDemoJobWriterConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<NewEmployees> dbOutputDemoJobFlatFileWriter() {
        JdbcBatchItemWriter<NewEmployees> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("insert into employees(emp_no,birth_date,first_name,last_name,gender,hire_date) values" + "(:emp_no,:birth_date,:first_name,:last_name,:gender,:hire_date)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }
}
