package com.xuecheng.framework.domain.learning;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name = "xc_learning_list")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcLearningList implements Serializable {

  private static final long serialVersionUID = -916357210051689799L;
  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  @Column(name = "course_id")
  private String courseId;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "start_time")
  private Date startTime;
  @Column(name = "end_time")
  private Date endTime;
  private String status;

}
