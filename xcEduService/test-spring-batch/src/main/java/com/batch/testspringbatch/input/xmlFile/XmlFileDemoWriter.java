package com.batch.testspringbatch.input.xmlFile;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("xmlFileDemoWriter")
public class XmlFileDemoWriter implements ItemWriter<NewEmployees> {
    @Override
    public void write(List<? extends NewEmployees> list) throws Exception {
        for (NewEmployees employee : list) {
            System.out.println(employee);
        }
    }
}
