package com.cdac.osvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OsvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsvsApplication.class, args);
	}

	@GetMapping("/")
	public String sayHello(){
		return " Hello world";
	}
}
