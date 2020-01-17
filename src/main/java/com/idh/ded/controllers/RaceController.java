package com.idh.ded.controllers;

import com.idh.ded.DTOs.Races;
import com.idh.ded.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "races")
public class RaceController {

    @Autowired
    RaceService raceService;

    //Pedir explicação de novo depois
    @GetMapping(value = "{racesId}")
    public ResponseEntity<Races> findOne(@PathVariable("racesId") String racesId) {
        Races racs = raceService.getOne(racesId);
        return ResponseEntity.status(HttpStatus.OK).body(racs);
    }
}
