package com.xuecheng.framework.domain.course;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@ToString
@Entity
@Table(name = "teachplan")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Teachplan implements Serializable {

	private static final long serialVersionUID = -916357110051689485L;
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	@Column(length = 32)
	private String id;
	private String pname;
	private String parentid;
	private String grade;
	private String ptype;
	private String description;
	private String courseid;
	private String status;
	private Integer orderby;
	private Double timelength;
	private String trylearn;

}
