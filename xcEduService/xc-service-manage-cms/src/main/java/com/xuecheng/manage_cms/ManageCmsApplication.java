package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ManageCmsApplication
 * @Description TODO
 * @Author 王晨
 * @Date 2018/12/9 20:41
 **/
@SpringBootApplication
//扫描实体类
@EntityScan("com.xuecheng.framework.domain.cms")
//扫描接口
@ComponentScan(basePackages = {"com.xuecheng.api"})
//扫描本项目下的所有类
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"})
//扫描common工程下的类
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class ManageCmsApplication {

	/**
	 * @Author wangc
	 * @Description 自己写的启动类
	 * @Date 20:42 2018/12/9
	 * @Param
	 **/
	public static void main(String[] args) {
		SpringApplication.run(ManageCmsApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}
