package com.idh.ded.services;

import com.idh.ded.DTOs.Class;
import com.idh.ded.DTOs.Spell;
import com.idh.ded.repositories.SpellRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
