package com.batch.testspringbatch.input.restart;

import com.batch.testspringbatch.bean.Employees;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("restartDemoReader")
public class RestartDemoReader implements ItemStreamReader<Employees> {

    private Long curLine = 0L;
    private boolean restart = false;
    private FlatFileItemReader<Employees> reader = new FlatFileItemReader<>();

    private ExecutionContext executionContext;

    public RestartDemoReader() {
        reader.setResource(new ClassPathResource("restartDemo.csv"));
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date"});

        DefaultLineMapper<Employees> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper((fieldSet -> Employees.builder()
                .empNo(fieldSet.readInt("emp_no"))
                .birthDate(fieldSet.readDate("birth_date"))
                .firstName(fieldSet.readString("first_name"))
                .lastName(fieldSet.readString("last_name"))
                .hireDate(fieldSet.readDate("hire_date"))
                .gender(fieldSet.readString("gender"))
                .build()));
        lineMapper.afterPropertiesSet();
        reader.setLineMapper(lineMapper);
    }

    @Override
    public Employees read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Employees employees = null;
        this.curLine++;
        if (restart) {
            reader.setLinesToSkip(this.curLine.intValue() - 1);
            restart = false;
            System.out.println("Start reading from line: " + this.curLine);
        }

        reader.open(this.executionContext);
        employees = reader.read();

        if (employees != null) {
            if (employees.getFirstName().equals("Sherlock")) {
                throw new RuntimeException("You got the best detective. His id is: " + employees.getEmpNo());
            }
        } else {
            curLine--;
        }
        return employees;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.executionContext = executionContext;
        if (executionContext.containsKey("curLine")) {
            this.curLine = executionContext.getLong("curLine");
            this.restart = true;
        } else {
            this.curLine = 0L;
            executionContext.put("curLine", this.curLine.intValue());
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("update curLine" + this.curLine);
        executionContext.put("curLine", this.curLine);
    }

    @Override
    public void close() throws ItemStreamException {

    }
}
