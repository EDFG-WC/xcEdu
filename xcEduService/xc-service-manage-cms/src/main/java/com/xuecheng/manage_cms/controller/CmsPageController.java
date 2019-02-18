package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CmsPageController
 * @Description TODO
 * @Author 王晨
 * @Date 2018/12/9 20:59
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

  @Autowired
  private PageService pageService;

  /**
   * @Author wangc
   * @Description 分页查询的方法
   * @Date 18:44 2018/12/9
   * @Param
   */
  @Override
  @GetMapping("/list/{page}/{size}")
  public QueryResponseResult findList(@PathVariable("page") Integer pageNum,
      @PathVariable("size") Integer pageSize,
      QueryPageRequest request) {
    //静态数据测试: 定义queryResponseResult;
	/*	QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
		List<CmsPage> list = new ArrayList<CmsPage>();
		CmsPage cmsPage = new CmsPage();
		cmsPage.setPageName("测试页面");
		list.add(cmsPage);
		queryResult.setList(list);
		queryResult.setTotal(1);
		QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
		return queryResponseResult;*/
    //调用service
    return pageService.findList(pageNum, pageSize, request);
  }

  @Override
  @GetMapping("/siteList")
  public QueryResponseResult findSiteList() {
    return pageService.findSiteList();

  }

  @Override
  @PostMapping("/add")
  public CmsPageResult add(@RequestBody CmsPage cmsPage) {
    return pageService.add(cmsPage);
  }

  @Override
  @GetMapping("/get/{id}")
  public CmsPage findById(@PathVariable("id") String id) {
    return pageService.findById(id);
  }

  @Override
  @PutMapping("/edit/{id}")
  public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
    return pageService.update(id, cmsPage);
  }

  @Override
  @DeleteMapping("/delete/{id}")
  public ResponseResult delete(@PathVariable("id") String id) {
    return pageService.delete(id);
  }
}
