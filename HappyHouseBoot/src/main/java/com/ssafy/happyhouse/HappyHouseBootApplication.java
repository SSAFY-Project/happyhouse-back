package com.ssafy.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = HappyHouseBootApplication.class)
@SpringBootApplication
public class HappyHouseBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseBootApplication.class, args);
	}

}
