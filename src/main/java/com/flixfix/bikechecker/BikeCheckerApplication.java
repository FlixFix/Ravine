package com.flixfix.bikechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BikeCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeCheckerApplication.class, args);
    }

}
