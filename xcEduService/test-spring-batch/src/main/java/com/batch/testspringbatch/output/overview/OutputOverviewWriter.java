package com.batch.testspringbatch.output.overview;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("outputOverviewWriter")
public class OutputOverviewWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println("Chunk size" + list.size());
        for (String item : list) {
            System.out.println(item);
        }
    }
}
