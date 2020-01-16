package com.idh.ded.controllers;

import com.idh.ded.services.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "dices")
public class DiceController {

    @Autowired
    DiceService diceService;

    @GetMapping(value = "/{dice}/{rolls}")
    public ResponseEntity<Map<String, Integer>> roll(@PathVariable int dice, @PathVariable int rolls) {
        Map<String, Integer> result = diceService.roll(dice, rolls);
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
