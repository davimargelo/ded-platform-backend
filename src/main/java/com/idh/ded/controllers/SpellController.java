package com.idh.ded.controllers;

import com.idh.ded.DTOs.Spell;
import com.idh.ded.services.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "spells")
public class SpellController {

    @Autowired
    SpellService spellService;

    @GetMapping
    public ResponseEntity<List<Spell>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.findAll());
    }

    @GetMapping("{spellId")
    public ResponseEntity<?> findOne(@PathVariable String spellId) {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.findOne(spellId));
    }

    @PostMapping
    public ResponseEntity<?> newSpell(@RequestBody Spell spell) {
        spellService.newSpell(spell);
        //TODO
        return null;
    }git
}
