package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.PageService;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {

  @Autowired
  private PageService pageService;
  @Test
  public void getHtmlTest() {
    String pageHtml = pageService.getPageHtml("5c6857c484e50222f4562500");
    System.out.println(pageHtml);
  }
}
