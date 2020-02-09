package com.idh.ded;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DedPlatformApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DedPlatformApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
