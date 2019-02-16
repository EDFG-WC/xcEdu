package com.xuecheng.test.freemarker.controller;


import com.xuecheng.test.freemarker.model.Student;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName FreemarkerController
 * @Description TODO
 * @Author 王晨
 * @Date 2019/1/13 21:01
 **/

@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/test1")
	public String test1(Map<String, Object> map) {
		map.put("name", "光*3");

		Student stu1 = new Student();
		stu1.setName("小明");
		stu1.setAge(18);
		stu1.setMoney(1000.86f);
		stu1.setBirthday(new Date());

		Student stu2 = new Student();
		stu2.setName("小红");
		stu2.setMoney(200.1f);
		stu2.setAge(19);
		stu2.setBirthday(new Date());

		//朋友列表
		List<Student> friends = new ArrayList<>();
		friends.add(stu1);
		//给第二个学生设置朋友列表
		stu2.setFriends(friends);
		//给第二个学生设置好友
		stu2.setBestFriend(stu1);

		List<Student> stus = new ArrayList<>();
		stus.add(stu1);
		stus.add(stu2);
		//向数据模型放map数据
		map.put("stus", stus);
		//准备map数据
		Map<String, Student> stuMap = new HashMap<>();
		stuMap.put("stu1", stu1);
		stuMap.put("stu2", stu2);
		//向数据模型放数据
		map.put("stu1", stu1);
		//向数据模型放map数据
		map.put("stuMap", stuMap);

		//返回freemarker模板的位置, 基于resources/templates的位置
		return "test1";
	}

	@RequestMapping("/banner")
	public String indexBanner(Map<String, Object> map) {
		//使用restTemplate去拿轮播图的模型数据
		String dataUrl = "http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f";
		ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
		Map body = forEntity.getBody();
		//设置模型数据
		map.putAll(body);
		return "index_banner";
	}
}
