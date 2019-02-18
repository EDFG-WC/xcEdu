package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName CmsPageControllerApi
 * @Description TODO
 * @Author 王晨
 * @Date 2018/12/9 17:08
 **/
@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

  /**
   * @Author wangc
   * @Description 分页查询的方法
   * @Date 18:44 2018/12/9
   * @Param
   */
  @ApiOperation("分页查询页面列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
      @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")})
  public QueryResponseResult findList(Integer pageNum, Integer pageSize,
      QueryPageRequest queryPageRequest);

  @ApiOperation("查询站点列表")
  public QueryResponseResult findSiteList();

  @ApiOperation("添加页面")
  public CmsPageResult add(CmsPage cmsPage);

  @ApiOperation("根据id查询页面")
  public CmsPage findById(String id);

  @ApiOperation("修改页面")
  public CmsPageResult edit(String id, CmsPage cmsPage);

  @ApiOperation("删除页面")
  public ResponseResult delete(String id);
}
