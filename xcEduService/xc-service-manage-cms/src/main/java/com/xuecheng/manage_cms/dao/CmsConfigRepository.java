package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName CmsConfigRepository
 * @Description TODO
 * @Author 王晨
 * @Date 2019/1/14 23:57
 **/
public interface CmsConfigRepository extends MongoRepository<CmsConfig, String> {

}
