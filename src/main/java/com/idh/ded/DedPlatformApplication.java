package com.idh.ded;

import com.idh.ded.DTOs.Class;
import com.idh.ded.DTOs.Races;
import com.idh.ded.DTOs.Spell;
import com.idh.ded.repositories.ClassRepository;

import com.idh.ded.repositories.RaceRepository;
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

	@Autowired
	RaceRepository raceRepository;

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

		//Adicionar mais racas depois
		raceRepository.saveAll(Arrays.asList(
				new Races("human", "Human", "Not much to talk about humans are basically just like you, with fighting skills, archery, sword use and Battle techniques.","races/human"),
				new Races("elf","Elf","Elves are described as tall and beautiful, similar to the Valar, but smaller in stature and power, and are immortal, at least as long as the World, called Arda, exists.","races/elf"),
				new Races("leprechaun","Leprechaun","","races/leprechaun"),
				new Races("dwarf","Dwarf","Dwarves are short in stature and are great masters in the art of mining and forging. They are usually allies of humans, but they do not like the elves, whom they look across with superiority.","races/dwarf"),
				new Races("khajiit","Khajiit","Coming from Arnos Province, Khajiit is smart, fast and agile. They make excellent thieves due to their natural invisibility.","races/khajiit"),
				new Races("hobbit","Hobbit","Hobbits are a discreet and very old people, usually no more than one meter high, are much less robust than dwarves and consider the possibility of participating in an adventure as an insane attitude,","races/hobbit")
			));
	}
}
