package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName QueryPageRequest
 * @Description 用于接收页面的查询条件
 * @Author 王晨
 * @Date 2018/12/9 16:44
 **/
@Data
@ToString
public class QueryPageRequest {

  //站点ID
  @ApiModelProperty("站点id")
  private String siteId;
  //页面ID
  @ApiModelProperty("页面ID")
  private String pageId;
  //页面名称
  @ApiModelProperty("页面名称")
  private String pageName;
  //别名
  @ApiModelProperty("页面别名")
  private String pageAlias;
  //模板ID
  @ApiModelProperty("模版id")
  private String templateId;

  //页面类型
  @ApiModelProperty("页面类型")
  private String pageType;
}
