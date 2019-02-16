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
@Table(name = "teachplan_media")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class TeachplanMedia implements Serializable {

	private static final long serialVersionUID = -916357110051689485L;
	@Id
	@GeneratedValue(generator = "jpa-assigned")
	@Column(name = "teachplan_id")
	private String teachplanId;

	@Column(name = "media_id")
	private String mediaId;

	@Column(name = "media_fileoriginalname")
	private String mediaFileOriginalName;

	@Column(name = "media_url")
	private String mediaUrl;
	private String courseId;

}
