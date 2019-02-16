package com.xuecheng.framework.domain.ucenter;

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
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name = "xc_company_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcCompanyUser implements Serializable {

	private static final long serialVersionUID = -916357110051689786L;
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	@Column(length = 32)
	private String id;
	@Column(name = "company_id")
	private String companyId;
	@Column(name = "user_id")
	private String userId;


}
