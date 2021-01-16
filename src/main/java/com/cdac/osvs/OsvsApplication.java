package com.cdac.osvs;

import com.cdac.osvs.util.RandomUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.cdac.osvs","controller"})
public class OsvsApplication {

	public static void main(String[] args) {

		//new File(System.getProperty(RandomUtil.uploadDirectory)).mkdir();

		SpringApplication.run(OsvsApplication.class, args);
	}
}
