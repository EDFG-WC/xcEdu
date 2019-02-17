package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

	@Autowired
	private CmsPageRepository cmsPageRepository;

	@Test
	public void testFindAll() {
		List<CmsPage> all = cmsPageRepository.findAll();
		System.out.println(all);
	}

	@Test
	public void testFindbyPage() {
		//分页参数, page参数从0开始
		Integer page = 1;
		Integer size = 10;
		//Pageable和PageRequest都是spring data的对象. 用来分页
		Pageable pageable = PageRequest.of(page, size);
		Page<CmsPage> all = cmsPageRepository.findAll(pageable);
		System.out.println("打印的结果:              " + all);
	}

	@Test
	public void testSaveInfo() {
		CmsPage cmsPage = new CmsPage();
		cmsPage.setSiteId("laowang9527");
		cmsPage.setTemplateId("laowang999");
		cmsPage.setPageName("老王的测试页面");
		cmsPage.setPageCreateTime(new Date());
		ArrayList<CmsPageParam> pageParamArrayList = new ArrayList<CmsPageParam>();
		CmsPageParam cmsPageParam = new CmsPageParam();
		cmsPageParam.setPageParamName("param9527");
		cmsPageParam.setPageParamValue("value9527");
		pageParamArrayList.add(cmsPageParam);
		cmsPage.setPageParams(pageParamArrayList);
		cmsPageRepository.save(cmsPage);
		System.out.println(cmsPage);
	}

	@Test
	public void testUpdate() {
		//查询对象
		Optional<CmsPage> optional = cmsPageRepository.findById("5a92141cb00ffc5a448ff1a0");
		//规范化对非空的判断
		if (optional.isPresent()) {
			CmsPage cmsPage = optional.get();
			//设置修改值
			cmsPage.setPageName("测试页面8848");
			//修改
			CmsPage save = cmsPageRepository.save(cmsPage);
			System.out.println(save);
		}
	}

	@Test
	public void testFindByPageName() {
		Optional<CmsPage> cmsPage = Optional
			.ofNullable(cmsPageRepository.findByPageName("测试页面8848"));
		//CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面8848");
		cmsPage.ifPresent(System.out::println);
		//System.out.println(cmsPage);
	}

	@Test
	public void testFindByExample() {
		//分页参数, page参数从0开始
		Integer page = 0;
		Integer size = 10;
		//Pageable和PageRequest都是spring data的对象. 用来分页
		Pageable pageable = PageRequest.of(page, size);
		CmsPage cmsPage = new CmsPage();
		cmsPage.setSiteId("laowang9527");
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
		Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
		/*Optional<Page<CmsPage>> all = Optional
			.ofNullable(cmsPageRepository.findAll(example, pageable));
		all.ifPresent(System.out::println);*/
		List<CmsPage> content = all.getContent();
		System.out.println(content);
	}
}