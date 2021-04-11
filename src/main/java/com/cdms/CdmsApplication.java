package com.cdms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdms.dao")
public class CdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdmsApplication.class, args);
	}

}
