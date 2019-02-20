package com.xuecheng.manage_cms.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CastException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName PageService
 * @Description TODO
 * @Author 王晨
 * @Date 2018/12/11 12:59
 **/
@Service
public class PageService {

  private Logger LOG = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private CmsPageRepository cmsPageRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CmsTemplateRepository cmsTemplateRepository;

  @Autowired
  private GridFsTemplate gridFsTemplate;

  @Autowired
  private GridFSBucket gridFSBucket;

  /**
   * @Author wangc
   * @Description 页面查询方法, 建议和对应的Controller类里的相关方法用一样的名字, 第一页从1开始
   * @Date 13:16 2018/12/11
   * @Param
   **/
  public QueryResponseResult findList(Integer pageNum, Integer pageSize,
      QueryPageRequest request) {
    if (request == null) {
      request = new QueryPageRequest();
    }
    //System.out.println(request);
    //条件匹配器
    //页面名称模糊查询, 需要自定义字符串的匹配器实现模糊查询
    ExampleMatcher exampleMatcher = ExampleMatcher
        .matching()
        .withMatcher("pageAlias", ExampleMatcher.GenericPropertyMatchers.contains())
        .withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains())
        .withMatcher("pageType", ExampleMatcher.GenericPropertyMatchers.exact());
    //页面信息
    CmsPage cmsPage = new CmsPage();

    //设置条件值（站点id）
    //Optional.ofNullable(request.getSiteId()).ifPresent(cmsPage::setSiteId);

    if (StringUtils.isNotEmpty(request.getSiteId())) {
      cmsPage.setSiteId(request.getSiteId());
    }

    //设置页面别名作为查询条件
    //Optional.ofNullable(request.getPageAlias()).ifPresent(cmsPage::setPageAlias);
    if (StringUtils.isNotEmpty(request.getPageAlias())) {
      cmsPage.setPageAlias(request.getPageAlias());
    }

    //设置页面名称
    if (StringUtils.isNotEmpty(request.getPageName())) {
      cmsPage.setPageName(request.getPageName());
    }

    //设置页面类型
    if (StringUtils.isNotEmpty(request.getPageType())) {
      cmsPage.setPageType(request.getPageType());
    }

    //页码逻辑
    if (pageNum <= 0) {
      pageNum = 1;
    }
    pageNum = pageNum - 1;
    if (pageSize <= 0) {
      pageSize = 10;
    }
    //条件查询
    Pageable pageable = PageRequest.of(pageNum, pageSize);
    Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
    Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
    QueryResult<CmsPage> queryResult = new QueryResult<>();
    //数据列表
    queryResult.setList(all.getContent());
    //数据总记录数
    queryResult.setTotal(all.getTotalElements());
    return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
  }

  /**
   * @Author eniac
   * @Description //TODO
   * @Date 13:15 2019/2/19
   * @Param
   **/
  public QueryResponseResult findSiteList() {
    List<CmsPage> all = cmsPageRepository.findAll();
    List<Map<String, String>> mapList = new ArrayList<>();
    for (CmsPage cmsPage : all) {
      Map<String, String> map = new HashMap<>();
      String siteId = cmsPage.getSiteId();
      String pageAlias = cmsPage.getPageAlias();
      map.put("siteId", siteId);
      map.put("siteName", pageAlias);
      mapList.add(map);
    }
    QueryResult queryResult = new QueryResult<>();
    //数据列表
    Optional.of(mapList).ifPresent(queryResult::setList);
    return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
  }

  public CmsPageResult add(CmsPage cmsPage) {
    Optional<CmsPage> cmsPageOpt = Optional
        .ofNullable(cmsPageRepository
            .findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(),
                cmsPage.getPageWebPath()));

    if (!cmsPageOpt.isPresent()) {
      cmsPage.setPageId(null);
      cmsPageRepository.save(cmsPage);
      return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }
    //抛出页面存在的异常:
    CastException.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
    return new CmsPageResult(CommonCode.FAIL, null);
  }

  //根据页面id查询页面
  public CmsPage findById(String id) {
    Optional<CmsPage> cmsOp = cmsPageRepository.findById(id);
    if (cmsOp.isPresent()) {
      return cmsOp.get();
    }
    return null;
    //Optional<CmsPage> cmsOp = cmsPageRepository.findById(id);
		/*Optional<CmsPage> cmsPage = Optional.of(cmsPageRepository.findById(id)).orElse(null);
		return cmsPage.get();*/
    //return Optional.of(cmsPageRepository.findById(id)).orElse(null).get();
  }

  //修改页面
  public CmsPageResult update(String id, CmsPage cmsPage) {
    //根据id查询页面信息
    CmsPage page = this.findById(id);
    if (page != null) {
      //更新模板id
      page.setTemplateId(cmsPage.getTemplateId());
      //更新所属站点
      page.setSiteId(cmsPage.getSiteId());
      //更新页面别名
      page.setPageAlias(cmsPage.getPageAlias());
      //更名页面名称
      page.setPageName(cmsPage.getPageName());
      //更新页面访问路径
      page.setPageWebPath(cmsPage.getPageWebPath());
      //更新物理路径
      page.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
      //更新dataUrl
      page.setDataUrl(cmsPage.getDataUrl());
      //执行更新
      CmsPage save = cmsPageRepository.save(page);
      if (save != null) {
        CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);
        return cmsPageResult;
      }
      //return Optional.of(new CmsPageResult(CommonCode.SUCCESS, cmsPageRepository.save(page))).orElse(new CmsPageResult(CommonCode.FAIL, null));
    }
    return new CmsPageResult(CommonCode.FAIL, null);
  }

  /**
   * @Author wangc
   * @Description //TODO 根据id删除页面的方法
   * @Date 23:22 2019/1/9
   * @Param
   **/
  public ResponseResult delete(String id) {
    CmsPage cmsPage = this.findById(id);
    if (cmsPage != null) {
      cmsPageRepository.delete(cmsPage);
      return new ResponseResult(CommonCode.SUCCESS);
    }
    return new ResponseResult(CommonCode.FAIL);
  }

  /**
   * 3、静态化程序远程请求DataUrl获取数据模型。 4、静态化程序获取页面的模板信息 5、执行页面静态化
   */
  //1. 获取页面的DataUrl+获取页面数据模型
  private Map getModelByPageId(String pageId) {
    //查询页面信息
    CmsPage cmsPage = this.findById(pageId);
    if (cmsPage == null) {
      //抛出页面不存在的异常
      CastException.cast(CmsCode.CMS_PAGE_DOESNOTEXIT);
    }
    //取出dataUrl
    String dataUrl = cmsPage.getDataUrl();
    if (StringUtils.isEmpty(dataUrl)) {
      CastException.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
    }
    ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
    Map body = forEntity.getBody();
    return body;
  }


  //2. 获取页面模板
  private String getTemplateByPageId(String pageId) {
    CmsPage cmsPage = this.findById(pageId);
    if (cmsPage == null) {
      //抛出页面不存在的异常
      CastException.cast(CmsCode.CMS_PAGE_DOESNOTEXIT);
    }
    //页面模板ID
    String templateId = cmsPage.getTemplateId();
    if (StringUtils.isEmpty(templateId)) {
      //页面模板为空
      CastException.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
    }
    Optional<CmsTemplate> cmsTemplateOpt = cmsTemplateRepository.findById(templateId);
    if (cmsTemplateOpt.isPresent()) {
      //获取模板文件id
      CmsTemplate cmsTemplate = cmsTemplateOpt.get();
      String fileId = cmsTemplate.getTemplateFileId();
      //根据id查询文件
      GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
      //打开下载流对象
      ObjectId objectId = gridFSFile.getObjectId();
      GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(objectId);
      //创建gridFsResource, 用于获取流对象
      GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
      try {
        //获取流中的数据
        String content = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        return content;
      } catch (IOException e) {
        LOG.error(e.getMessage());
        //e.printStackTrace();
      }
    }
    return null;
  }

  //3. 执行页面静态化
  private String generateHtml(String templateContent, Map model) {
    //创建配置对象
    Configuration configuration = new Configuration(Configuration.getVersion());
    //模板加载器
    StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
    stringTemplateLoader.putTemplate("template", templateContent);
    //向configuration配置模板加载器
    configuration.setTemplateLoader(stringTemplateLoader);
    //获取模板
    try {
      Template template = configuration.getTemplate("template");
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    } catch (Exception e) {
      LOG.error(e.getMessage());
    }
    return null;
  }

  /**
   * @Author wangc
   * @Description //TODO 获取页面的url
   * @Date 23:40 2019/1/26
   * @Param
   **/
  // 执行页面静态化, 获取页面的Html
  public String getPageHtml(String pageId) {
    //1. 获取页面模型数据
    Map model = this.getModelByPageId(pageId);
    if (model == null) {
      //获取页面模型数据为空
      CastException.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
    }
    //2. 获取页面模板
    String templateContent = getTemplateByPageId(pageId);
    if (StringUtils.isEmpty(templateContent)) {
      //页面模板为空
      CastException.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
    }
    //3. 执行页面静态化
    String html = generateHtml(templateContent, model);
    if (StringUtils.isEmpty(html)) {
      //静态化页面为空
      CastException.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
    }
    return html;
  }

}
