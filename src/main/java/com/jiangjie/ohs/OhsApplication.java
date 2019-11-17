package com.jiangjie.ohs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.jiangjie.ohs.mapper")
@RestController
public class OhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhsApplication.class, args);
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello, OHS";
	}
	
	@PostMapping("/testPostRequest")
	@ResponseBody
	public String testPostRequest() {
		return "post 请求成功，当前时间为：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}

}

