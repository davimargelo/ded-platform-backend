package com.idh.ded.controllers;

import com.idh.ded.DTOs.Dice;
import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.repositories.DicePresetsRepository;
import com.idh.ded.services.DiceService;
import org.apache.http.client.HttpResponseException;
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

    @Autowired
    DicePresetsRepository dicePresetsRepository;

    @GetMapping(value = "/{dice}/{rolls}")
    public ResponseEntity<Map<String, Integer>> roll(@PathVariable int dice, @PathVariable int rolls) {
        Map<String, Integer> result = diceService.roll(dice, rolls);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "create-preset/{presetName}")
    public ResponseEntity<?> createPreSet(@PathVariable String presetName, @RequestBody List<Map<String, Integer>> dices) {
        System.out.println(presetName + "\n" + dices);
        List<Dice> diceList = new ArrayList<>();

        try {
            for (Map<String, Integer> dice : dices) {
                diceList.add(new Dice(dice.get("d"), dice.get("rolls")));
            }

            DicePreset dicePreset = diceService.createPreset(presetName, diceList);

            for (Dice dice : dicePresetsRepository.getOne(presetName).getDiceList()) {
                System.out.println("d" + dice.getD() + " rolls: " + dice.getRolls());
            }
            return ResponseEntity.status(HttpStatus.OK).body(dicePreset);
        } catch (HttpResponseException e) {
//            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode()).body(e.getReasonPhrase());
        }
    }
}
