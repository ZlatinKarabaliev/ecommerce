package com.ecommerce.server.demo;

import com.ecommerce.server.demo.model.trg.User;
import com.ecommerce.server.demo.repository.ProductRepository;
import com.ecommerce.server.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {


	@Autowired
	private ObjectMapper objectMapper;
	private UserRepository userRepository;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
