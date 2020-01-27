package com.idh.ded.controllers;

import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.services.DiceService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "dices")
public class DiceController {

    @Autowired
    DiceService diceService;

    @Deprecated
    @GetMapping(value = "/rollers")
    public ResponseEntity<?> findAllRollers() {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.findRollers());
    }

    @ApiOperation(value = "Number of sides / number of rolls = to generate a random play")
    @GetMapping(value = "/roll/{dice}/{rolls}")
    public ResponseEntity<Map<String, Integer>> roll(@PathVariable int dice, @PathVariable int rolls) {
        Map<String, Integer> result = diceService.roll(dice, rolls);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Rolls a preset")
    @GetMapping(value = "/roll/{presetName}")
    public ResponseEntity<?> rollPreset(@PathVariable String presetName) {
        List<Map<String, Integer>> result = diceService.rollPreset(presetName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Get all Presets")
    @GetMapping
    public ResponseEntity<List<DicePreset>> getAllPresets() {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.getAllDicePresets());
    }

    @ApiOperation(value = "Creates a new preset sending an array of objects {\"d\": Integer, \"rolls\": Integer}")
    @PostMapping(value = "/{presetName}")
    public ResponseEntity<?> createPreSet(@PathVariable String presetName, @RequestBody List<Map<String, Integer>> dices) {
        System.out.println(presetName + "\n" + dices);
            DicePreset dicePreset = diceService.createPreset(presetName, dices);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .buildAndExpand(dicePreset.getName()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Gets a preset by name")
    @GetMapping(value = "/{presetName}")
    public ResponseEntity<DicePreset> getOnePreset(@PathVariable String presetName) {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.findOne(presetName));
    }

    @PutMapping(value = "/{presetName}")
    public ResponseEntity<?> updatePresetDiceList(@PathVariable String presetName, @RequestBody List<Map<String, Integer>> dices) {
            return ResponseEntity.status(HttpStatus.OK).body(diceService.updatePresetDicelist(presetName, dices));
    }

    @ApiOperation(value = "Updates a preset names + dicelist by sending a new array of objects: {\"d\": Integer, \"rolls\": Integer}")
    @PutMapping(value = "/{presetName}/{newPresetName}")
    public ResponseEntity<?> updatePreset(@PathVariable String presetName, @PathVariable String newPresetName, @RequestBody List<Map<String, Integer>> dices) {
            return ResponseEntity.status(HttpStatus.OK).body(diceService.updatePreset(presetName, newPresetName, dices));
    }

    @ApiOperation(value = "Deletes a preset by name")
    @DeleteMapping(value = "/{presetName}")
    public ResponseEntity<?> deletePreset(@PathVariable String presetName) {
        diceService.deletePreset(presetName);
        return ResponseEntity.status(HttpStatus.OK).body(presetName + " deleted!");
    }
}
