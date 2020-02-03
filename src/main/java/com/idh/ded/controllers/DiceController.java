package com.idh.ded.controllers;

import com.idh.ded.domain.Dice;
import com.idh.ded.domain.DicePreset;
import com.idh.ded.dtos.DicePresetDTO;
import com.idh.ded.services.DiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    @GetMapping(value = "/roll")
    public ResponseEntity<Map<String, String>> roll(
            @RequestParam(value = "dice", defaultValue = "6") Integer dice,
            @RequestParam(value = "rolls", defaultValue = "1")Integer rolls) {
        Map<String, String> result = diceService.roll(dice, rolls);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Rolls a preset")
    @GetMapping(value = "/roll/{presetId}")
    public ResponseEntity<?> rollPreset(@PathVariable Integer presetId) {
        List<Map<String, String>> result = diceService.rollPreset(presetId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Get all Presets")
    @GetMapping
    public ResponseEntity<List<DicePreset>> getAllPresets() {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.getAllDicePresets());
    }

    @ApiOperation(value = "Get all Presets By PAGE + search name")
    @GetMapping(value = "page")
    public ResponseEntity<Page<DicePreset>> getAllPresetsByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10")Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.getAllDicePresetsByPage(name, page, size, orderBy, direction));
    }

    @ApiOperation(value = "Creates a new preset")
    @PostMapping
    public ResponseEntity<?> createPreSet(@Valid @RequestBody DicePresetDTO dicePresetDTO) {
        System.out.println(dicePresetDTO.getName() + "\n" + dicePresetDTO.getDiceList());
            DicePreset dicePreset = diceService.createPreset(dicePresetDTO.getName(), dicePresetDTO.getDiceList());
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .buildAndExpand(dicePreset.getName()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Gets a preset by Id")
    @GetMapping(value = "/{presetId}")
    public ResponseEntity<DicePreset> getOnePreset(@PathVariable Integer presetId) {
        return ResponseEntity.status(HttpStatus.OK).body(diceService.findOne(presetId));
    }

//    @Deprecated
//    @PutMapping(value = "/{presetName}")
//    public ResponseEntity<?> updatePresetDiceList(@PathVariable String presetName, @RequestBody List<Dice> dices) {
//            return ResponseEntity.status(HttpStatus.OK).body(diceService.updatePresetDicelist(presetName, dices));
//    }

    @ApiOperation(value = "Updates a preset names + dicelist")
    @PutMapping(value = "/{presetId}")
    public ResponseEntity<?> updatePreset(@PathVariable Integer presetId, @Valid @RequestBody DicePresetDTO dicePresetDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(diceService.updatePreset(presetId, dicePresetDTO.getName(), dicePresetDTO.getDiceList()));
    }

    @ApiOperation(value = "Deletes a preset by Id")
    @DeleteMapping(value = "/{presetId}")
    public ResponseEntity<?> deletePreset(@PathVariable Integer presetId) {
        diceService.deletePreset(presetId);
        return ResponseEntity.status(HttpStatus.OK).body("Preset ID: " + presetId + " deleted!");
    }
}
