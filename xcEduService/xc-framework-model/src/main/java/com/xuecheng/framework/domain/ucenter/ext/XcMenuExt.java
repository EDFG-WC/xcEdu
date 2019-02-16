package com.xuecheng.framework.domain.ucenter.ext;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.ucenter.XcMenu;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class XcMenuExt extends XcMenu {

	List<CategoryNode> children;
}
