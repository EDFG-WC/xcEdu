package com.batch.testspringbatch.input.overview;


import com.batch.testspringbatch.listenerDemo.InputOverviewDemoItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InputOverviewDemoJobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job InputOverviewDemoJob() {
        return jobBuilderFactory.get("InputOverviewDemoJob")
                .start(inputOverviewDemoStep())
                .build();
    }

    private Step inputOverviewDemoStep() {
        return stepBuilderFactory.get("inputOverviewDemoStep")
                .<String, String>chunk(2)
                .reader(inputOverviewDemoReader())
                .writer(list -> {
                    for (String item : list) {
                        System.out.println("current item is: " + item);
                    }
                }).build();
    }

    private InputOverviewDemoItemReader inputOverviewDemoReader() {
        List<String> data = Arrays.asList("1", "2", "3");
        return new InputOverviewDemoItemReader(data);
    }
}
