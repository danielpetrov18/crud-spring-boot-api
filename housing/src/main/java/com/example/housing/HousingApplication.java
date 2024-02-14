package com.example.housing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HousingApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(HousingApplication.class, args);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			//e.printStackTrace();
		}

	}

}
