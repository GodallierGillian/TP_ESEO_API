package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dao.VilleDaoImpl;

@SpringBootApplication
public class Application {
	private static final Logger logger 
	  = LoggerFactory.getLogger(VilleDaoImpl.class);
	public static void main (String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Application Started !");
	}
}
