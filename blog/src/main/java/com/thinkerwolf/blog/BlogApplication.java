package com.thinkerwolf.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages={"com.thinkerwolf.blog"})
//@ImportResource("classpath:spring-security.xml")
public class BlogApplication {

    public static void main(String[] args) {
    	SpringApplication.run(BlogApplication.class, args);
    	
    	
    }
}
