package com.techwasti.ex.testcontainerdemo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TestcontainerdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainerdemoApplication.class, args);
	}

}
