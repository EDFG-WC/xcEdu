package com.batch.testspringbatch.input.multiple;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("flatFileDemoWriter")
public class FlatFileDemoWriter implements ItemWriter<Employees> {
    @Override
    public void write(List<? extends Employees> list) throws Exception {
        for (Employees employee : list) {
            System.out.println(employee);
        }
    }
}