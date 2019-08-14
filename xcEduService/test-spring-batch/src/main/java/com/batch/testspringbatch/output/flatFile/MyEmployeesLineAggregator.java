package com.batch.testspringbatch.output.flatFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.file.transform.LineAggregator;

public class MyEmployeesLineAggregator implements LineAggregator<NewEmployees> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String aggregate(NewEmployees newEmployees) {
        try {
            return mapper.writeValueAsString(newEmployees);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException("Something wrong.", e);
        }
    }
}
