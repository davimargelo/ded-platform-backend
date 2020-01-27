package com.idh.ded.services;

import com.idh.ded.domain.Spell;
import com.idh.ded.repositories.SpellRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpellService {

    @Autowired
    SpellRepository spellRepository;

    public List<Spell> findAll() {
        return spellRepository.findAll();
    }

    public Spell findOne(String spellId) {
        Optional<Spell> spell = spellRepository.findById(spellId);
        return spell.orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + spellId, new Throwable("Type: " + Spell.class.getName())));
    }

    public void newSpell(Spell spell) {
        //TODO
    }

    @Deprecated
    public Map<String, String> cast(String spellId) {

        spellRepository.findById(spellId).orElseThrow(() ->
                new ObjectNotFoundException("404 - Not Found ID: " + spellId, new Throwable("Type: " + Spell.class.getName())));

        int d = 20;
        int rand_dice = 0;

        Random rand = new Random();

        while (rand_dice == 0)
            rand_dice = rand.nextInt(d);

        Map<String, String> spellCastResult = new HashMap<>();

        spellCastResult.put("spell", spellId);
        spellCastResult.put("roll", String.valueOf(rand_dice));
        //TODO
        return spellCastResult;
    }
}
