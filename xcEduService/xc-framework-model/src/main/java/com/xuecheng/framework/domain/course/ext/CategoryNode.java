package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.Category;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@ToString
public class CategoryNode extends Category {

  List<CategoryNode> children;

}
