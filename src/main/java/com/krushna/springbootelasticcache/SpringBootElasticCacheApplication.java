package com.krushna.springbootelasticcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootElasticCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticCacheApplication.class, args);
	}

}
