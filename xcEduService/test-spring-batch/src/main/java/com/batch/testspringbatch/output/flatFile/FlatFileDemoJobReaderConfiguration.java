package com.batch.testspringbatch.output.flatFile;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class FlatFileDemoJobReaderConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<NewEmployees> flatFileDemoJdbcPagingReader() {
        JdbcPagingItemReader<NewEmployees> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper((rs, rowNum)->{
            return NewEmployees.builder()
                    .emp_no(rs.getInt("emp_no"))
                    .birth_date(rs.getDate("birth_date"))
                    .first_name(rs.getString("first_name"))
                    .last_name(rs.getString("last_name"))
                    .hire_date(rs.getDate("hire_date"))
                    .gender(rs.getString("gender"))
                    .build();
        });

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("emp_no, birth_date, first_name, last_name, hire_date, gender");
        queryProvider.setFromClause("from employees");
        HashMap<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("emp_no", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);
        return reader;
    }
}
