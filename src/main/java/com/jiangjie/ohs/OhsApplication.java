package com.jiangjie.ohs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiangjie.ohs.mapper")
public class OhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhsApplication.class, args);
	}

}

