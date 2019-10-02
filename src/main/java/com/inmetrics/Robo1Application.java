package com.inmetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inmetrics.DAO.MongoDAO;

@SpringBootApplication
public class Robo1Application extends MongoDAO {

	public static void main(String[] args) {
		SpringApplication.run(Robo1Application.class, args);
	}

}
