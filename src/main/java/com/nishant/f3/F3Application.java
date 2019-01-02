package com.nishant.f3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nishant.database.DatabaseSequence;
import com.nishant.database.QuestionSetRepository;
import com.nishant.database.ResultSetRepo;
import com.nishant.database.UserRepository;
import com.nishant.model.ResultSetModel;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses= {DatabaseSequence.class,UserRepository.class,QuestionSetRepository.class,ResultSetRepo.class})
@ComponentScan("com.nishant.database")
@ComponentScan("com.nishant")
public class F3Application {

	public static void main(String[] args) {
		System.out.println("here1");
		SpringApplication.run(F3Application.class, args);
	}

}

