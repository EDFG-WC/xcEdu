package com.xuecheng.framework.domain.ucenter.ext;

import com.xuecheng.framework.domain.ucenter.XcMenu;
import com.xuecheng.framework.domain.ucenter.XcUser;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class XcUserExt extends XcUser {

	//权限信息
	private List<XcMenu> permissions;

	//企业信息
	private String companyId;
}
