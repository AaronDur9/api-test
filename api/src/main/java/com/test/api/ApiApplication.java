package com.test.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	@Bean
	CarRepository carRepository() {
	    //Here we should fill the DB with data in memory just as workaround
		return new CarRepository();
	}

    @Bean
    UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    DemandRepository demandRepository() { return new DemandRepository();}

    @Bean
    SchedulingProcessor schedulingProcessor() { return new SchedulingProcessor();}



	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
