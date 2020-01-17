package com.idh.ded.controllers;

import com.idh.ded.DTOs.Dice;
import com.idh.ded.services.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @PostMapping(value = "create-preset/{presetName}")
    public ResponseEntity<?> createPreSet(@PathVariable String presetName, @RequestBody List<Map<String, Integer>> dices) {
        System.out.println(presetName + "\n" + dices);
        List<Dice> diceList = new ArrayList<>();

        for (Map<String, Integer> dice : dices) {
            diceList.add(new Dice(dice.get("d"), dice.get("rolls")));
        }
        diceService.createPreset(presetName, diceList);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
