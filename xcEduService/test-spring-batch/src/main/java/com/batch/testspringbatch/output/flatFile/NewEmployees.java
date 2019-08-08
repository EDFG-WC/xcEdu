package com.batch.testspringbatch.output.flatFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewEmployees {
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private Date hire_date;
}
