package com.mbaigo.trainingtools.training_tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TrainingToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingToolsApplication.class, args);
	}

}
