package com.idh.ded.controllers;

import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.services.DiceService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "dices")
public class DiceController {

    @Autowired
    DiceService diceService;

    @GetMapping(value = "/roll/{dice}/{rolls}")
    public ResponseEntity<Map<String, Integer>> roll(@PathVariable int dice, @PathVariable int rolls) {
        Map<String, Integer> result = diceService.roll(dice, rolls);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/roll/{presetName}")
    public ResponseEntity<?> rollPreset(@PathVariable String presetName) {
        List<Map<String, Integer>> result = diceService.rollPreset(presetName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<DicePreset>> getAllPresets() {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.getAllDicePresets());
    }

    @PostMapping(value = "/{presetName}")
    public ResponseEntity<?> createPreSet(@PathVariable String presetName, @RequestBody List<Map<String, Integer>> dices) {
        System.out.println(presetName + "\n" + dices);

        try {
            DicePreset dicePreset = diceService.createPreset(presetName, dices);

            return ResponseEntity.status(HttpStatus.OK).body(dicePreset);
        } catch (HttpResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReasonPhrase());
        }
    }

    @GetMapping(value = "/{presetName}")
    public ResponseEntity<DicePreset> getOnePreset(@PathVariable String presetName) {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.findOne(presetName));
    }

    @PutMapping(value = "/{presetName}/{newPresetName}")
    public ResponseEntity<?> updatePreset(@PathVariable String presetName, @PathVariable String newPresetName, @RequestBody List<Map<String, Integer>> dices) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(diceService.updatePreset(presetName, newPresetName, dices));
        } catch (HttpResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReasonPhrase());
        }
    }

    @DeleteMapping(value = "/{presetName}")
    public ResponseEntity<?> deletePreset(@PathVariable String presetName) {
        diceService.deletePreset(presetName);
        return ResponseEntity.status(HttpStatus.OK).body(presetName + " deleted!");
    }
}
