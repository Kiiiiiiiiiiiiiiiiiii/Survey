package com.example.surveyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class SurveyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyServerApplication.class, args);
	}

}
