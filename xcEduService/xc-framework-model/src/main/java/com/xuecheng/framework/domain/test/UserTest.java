package com.xuecheng.framework.domain.test;

import java.util.Date;
import javax.persistence.Column;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mrt on 2018/3/30.
 */
@Data
@ToString
@Document(collection = "user_test")
public class UserTest {


	@Id
	private String id;
	private String name;

	@Column(name = "create_time")
	private Date createTime;
}
