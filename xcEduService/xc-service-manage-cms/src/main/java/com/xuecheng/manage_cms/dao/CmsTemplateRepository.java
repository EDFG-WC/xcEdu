package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName CmsTemplateRepository
 * @Description TODO
 * @Author 王晨
 * @Date 2019/1/22 1:02
 **/
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {

}
