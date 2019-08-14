package com.batch.testspringbatch.output.flatFile;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

@Configuration
public class FlatFileDemoJobWriterConfiguration {

    @Bean
    public FlatFileItemWriter<NewEmployees> flatFileDemoJdbcPagingWriter() throws Exception {
        FlatFileItemWriter<NewEmployees> itemWriter = new FlatFileItemWriter<>();
        String path = File.createTempFile("employeeInfo", ".data").getAbsolutePath();
        System.out.println("--->file is created in " + path);
        itemWriter.setResource(new FileSystemResource(path));

        itemWriter.setLineAggregator(new MyEmployeesLineAggregator());
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }
}
