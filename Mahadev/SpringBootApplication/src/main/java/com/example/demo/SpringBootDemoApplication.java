package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.example.demo.controller",
		"com.example.demo.service",
		"com.example.demo.model",
		"com.example.demo.dao",
		"com.example.demo.exception"
		}) 
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
