package com.idh.ded;

import com.idh.ded.DTOs.Class;
import com.idh.ded.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DedPlatformApplication implements CommandLineRunner {
	@Autowired
	ClassRepository classRepository;
	public static void main(String[] args) {
		SpringApplication.run(DedPlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		classRepository.saveAll(Arrays.asList(
				new Class("barbarian", "Barbarian", "/classes/barbarian"),
				new Class("bard", "Bard", "classes/barbarian")
		));
	}
}
