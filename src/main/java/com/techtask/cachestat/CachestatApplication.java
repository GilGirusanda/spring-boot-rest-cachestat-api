package com.techtask.cachestat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CachestatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachestatApplication.class, args);
	}

}
