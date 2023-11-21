package com.SplitwiseClone.SplitwiseClone;

import com.SplitwiseClone.SplitwiseClone.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitwiseCloneApplication implements CommandLineRunner {

	@Autowired
	private InitService initService;

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseCloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Runner from command line ");
		initService.initialise();
	}
}
