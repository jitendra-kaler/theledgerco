package com.theledgerco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.theledgerco.service.GeektrustService;

@SpringBootApplication
public class BankOperationsApplication implements CommandLineRunner {

	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	GeektrustService service;
	@Value("${theledgerco.input-type}")
	String inputType;
	@Value("${theledgerco.filename}")
	String inputFile;

	public static void main(String[] args) {
		SpringApplication.run(BankOperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (inputType.equalsIgnoreCase("file")) {
			Resource resource = resourceLoader.getResource("classpath:" + inputFile);
			InputStream inputStream = resource.getInputStream();
			service.parseFile(inputStream);
		}
	}

}
