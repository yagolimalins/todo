package dev.yagolins.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
        System.out.println("\uD83C\uDF43 Spring Boot @ port 8080 | Swagger UI @ http://localhost:8080/swagger.html");
	}

}
