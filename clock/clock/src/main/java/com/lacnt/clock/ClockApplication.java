package com.lacnt.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableScheduling
public class ClockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockApplication.class, args);
	}

	@RestController
	public class MyController {
		@RequestMapping("/hello")
		public String HelloWord() {
			return "ok";
		}
	}

}
