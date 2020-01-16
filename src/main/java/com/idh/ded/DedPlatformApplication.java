package com.idh.ded;

import com.idh.ded.DTOs.Class;
import com.idh.ded.DTOs.Spell;
import com.idh.ded.repositories.ClassRepository;
import com.idh.ded.repositories.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DedPlatformApplication implements CommandLineRunner {
	@Autowired
	ClassRepository classRepository;

	@Autowired
	SpellRepository spellRepository;

	public static void main(String[] args) {
		SpringApplication.run(DedPlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		classRepository.saveAll(Arrays.asList(
				new Class("barbarian", "Barbarian", "/classes/barbarian"),
				new Class("bard", "Bard", "classes/barbarian")
		));

		spellRepository.saveAll(Arrays.asList(
				new Spell("fireball", "Fire Ball", "Throws a fire ball and set fire on enemy","spells/fireball"),
				new Spell("powerslash", "Power Slash", "Successfully hit a single target in the front row with stored up power.", "spells/powerslash")
		));
	}
}
