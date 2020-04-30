package com.loststars.dynamic.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.loststars.dynamic.datasource.dao")
@EnableTransactionManagement
public class DynamicDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceApplication.class, args);
	}

}
