package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.PageService;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName
 * @Description 用于页面预览的controller
 * @Author 王晨
 * @Date
 **/
@Controller
@RequestMapping("/cms/preview")
public class CmsPagePreviewController extends BaseController {

  //private static final Logger logger = Logger.getLogger(CmsPagePreviewController.class);

  @Resource
  private PageService pageService;

  @RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
  public void preview(@PathVariable("pageId") String pageId) {
    String pageHtml = pageService.getPageHtml(pageId);
    if (StringUtils.isNotEmpty(pageHtml)) {
      try {
        @Cleanup
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(pageHtml.getBytes("utf‐8"));
      } catch (Exception e) {

      }
    }
  }
}
