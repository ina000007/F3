package com.nishant.f3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.nishant.database.UserRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses=UserRepository.class)
@ComponentScan("com.nishant.database")
@ComponentScan("com.nishant")
public class F3Application {

	public static void main(String[] args) {
		System.out.println("here1");
		SpringApplication.run(F3Application.class, args);
	}

}

