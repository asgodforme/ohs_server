package com.jiangjie.ohs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OhsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test1() {
//		String url = "http://localhost:8888/api/menu/getAllMenu";
//		String msg = restTemplate.getForObject(url, String.class);
//		System.out.println("----------------------------- \n -" + msg);

	}
}
