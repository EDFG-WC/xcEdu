package com.batch.testspringbatch.input.xmlFile;

import com.thoughtworks.xstream.converters.ConverterMatcher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XmlFileDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("xmlFileDemoWriter")
    private ItemWriter<? super NewEmployees> xmlFileDemoWriter;

    @Bean
    public Job xmlFileDemoJob() throws ClassNotFoundException {
        return jobBuilderFactory.get("xmlFileDemoJob")
                .start(xmlFileDemoStep())
                .build();
    }

    @Bean
    public Step xmlFileDemoStep() throws ClassNotFoundException {
        return stepBuilderFactory.get("xmlFileDemoStep")
                .<NewEmployees, NewEmployees>chunk(1000)
                .reader(xmlFileDemoReader())
                .writer(xmlFileDemoWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<NewEmployees> xmlFileDemoReader() throws ClassNotFoundException {
        StaxEventItemReader<NewEmployees> reader = new StaxEventItemReader<>();

        reader.setResource(new ClassPathResource("employees.xml"));
        reader.setFragmentRootElementName("RECORD");
        //反序列化
        XStreamMarshaller unMarshaller = new XStreamMarshaller();
        ConverterMatcher[] converterMatchers = {new DateConverter()};
        unMarshaller.setConverters(converterMatchers);
        //设置反序列化时的映射对象
        Map<String, Class> map = new HashMap<>(1);
        map.put("RECORD", NewEmployees.class);
        unMarshaller.setAliases(map);
        reader.setUnmarshaller(unMarshaller);
        return reader;
    }
}
