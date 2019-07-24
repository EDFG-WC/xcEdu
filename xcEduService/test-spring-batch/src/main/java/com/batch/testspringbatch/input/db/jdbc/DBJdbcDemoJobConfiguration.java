package com.batch.testspringbatch.input.db.jdbc;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DBJdbcDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("dbJdbcDemoWriter")
    private ItemWriter<? super Employees> dbJdbcDemoWriter;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job DBJdbcDemoJob() {
        return jobBuilderFactory.get("DBJdbcDemoJob")
                .start(dbjdbcDemoStep())
                .build();
    }

    @Bean
    public Step dbjdbcDemoStep() {
        return stepBuilderFactory.get("DBJdbcDemoStep")
                .<Employees, Employees>chunk(100)
                .reader(dbJdbcDemoReader())
                .writer(dbJdbcDemoWriter)
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Employees> dbJdbcDemoReader() {
        JdbcPagingItemReader<Employees> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(100);
        //如何把结果集映射为Employees对象的规则
        reader.setRowMapper((rs, rowNum) -> {
            return Employees.builder()
                    .empNo(rs.getInt("emp_no"))
                    .birthDate(rs.getDate("birth_date"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .hireDate(rs.getDate("hire_date"))
                    .gender(rs.getString("gender"))
                    .build();
        });
        //sql:
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("emp_no, birth_date, first_name, last_name, gender, hire_date");
        queryProvider.setFromClause("from Employees");
        //排序:
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("emp_no", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);
        return reader;
    }
}
