package com.xuecheng.test.freemarker.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Student
 * @Description TODO
 * @Author 王晨
 * @Date 2019/1/13 20:24
 **/

@Data
@ToString
public class Student {

	private String name;

	private int age;

	private Date birthday;

	private Float money;

	private List<Student> friends;

	private Student bestFriend;
}
