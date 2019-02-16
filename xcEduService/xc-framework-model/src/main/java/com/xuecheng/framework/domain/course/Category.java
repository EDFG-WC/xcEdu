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
@Table(name = "category")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Category implements Serializable {

	private static final long serialVersionUID = -906357110051689484L;
	@Id
	@GeneratedValue(generator = "jpa-assigned")
	@Column(length = 32)
	private String id;
	private String name;
	private String label;
	private String parentid;
	private String isshow;
	private Integer orderby;
	private String isleaf;

}
