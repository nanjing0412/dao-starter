package com.dragon.testdaospringbootstarter;

import com.github.jun.starter.dao.dsp.annotation.EnableDaoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDaoAutoConfiguration
@SpringBootApplication
public class TestDaoSpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDaoSpringBootStarterApplication.class, args);
	}

}

