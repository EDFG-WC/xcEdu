package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CmsConfigService
 * @Description TODO
 * @Author 王晨
 * @Date 2019/1/15 0:04
 **/
@Service
public class CmsConfigService {

  @Autowired
  private CmsConfigRepository cmsConfigRepository;

  public CmsConfig getConfigById(String id) {
    CmsConfig cmsConfig = Optional.of(cmsConfigRepository.findById(id)).orElse(null).get();
    return cmsConfig;
  }
}
