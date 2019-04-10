package com.thinkerwolf.blogmg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages={"com.thinkerwolf.blogmg"})
@ImportResource(value="classpath:spring-acgei-security.xml")
public class BlogManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogManageApplication.class, args);
	}
}
