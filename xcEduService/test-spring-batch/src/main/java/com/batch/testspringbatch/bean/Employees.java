package com.batch.testspringbatch.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employees {
    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
}
