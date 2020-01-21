package com.idh.ded.services;

import com.idh.ded.DTOs.Dice;
import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.repositories.DicePresetsRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceService {

    @Autowired
    DicePresetsRepository dicePresetsRepository;

    public Map<String, Integer> roll(Integer dice, Integer rolls) {
        Map<String, Integer> result = new HashMap<>();
        Random rand = new Random();
        int roll = 0;
        int sum = 0;
        List<Integer> rollResults = new ArrayList<>();

        for (int i = 0; i < rolls; i++) {
            while (roll == 0)
                roll = rand.nextInt(dice + 1);
            rollResults.add(roll);
            roll = 0;
        }

        for (int value : rollResults) {
            sum += value;
        }
        result.put(rollResults.toString()
                .replace(",", " +")
                .replace("[", "")
                .replace("]", ""), sum);
        return result;
    }

    public List<Map<String, Integer>> rollPreset(String presetName) {
        DicePreset preset = dicePresetsRepository.findById(presetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + presetName, new Throwable("Type: " + DicePreset.class.getName())));

        List<Map<String, Integer>> result = new ArrayList<>();

        for (Dice dice : preset.getDiceList()) {
            result.add(roll(dice.getD(), dice.getRolls()));
        }
        return result;
    }

    public DicePreset createPreset(String presetName, List<Map<String, Integer>> dices) throws HttpResponseException {

        if (dicePresetsRepository.existsById(presetName))
            throw new HttpResponseException(HttpStatus.CONFLICT.value(), "This Preset Name is been used");

        List<Dice> diceList = new ArrayList<>();

        for (Map<String, Integer> dice : dices) {
            diceList.add(new Dice(dice.get("d"), dice.get("rolls")));
        }

        DicePreset dicePreset = new DicePreset(presetName);

        dicePreset.getDiceList().addAll(diceList);

        for (Dice d : diceList) {
            d.getDicePresetList().add(dicePreset);
        }

        dicePresetsRepository.save(dicePreset);

        return dicePreset;
    }

    public DicePreset findOne(String presetName) {
        return dicePresetsRepository.findById(presetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + presetName, new Throwable("Type: " + DicePreset.class.getName())));
    }

    public List<DicePreset> getAllDicePresets() {
        return dicePresetsRepository.findAll();
    }

    public DicePreset updatePreset(String presetName, String newPresetName, List<Map<String, Integer>> dices) throws HttpResponseException {
        if (dicePresetsRepository.existsById(newPresetName))
            throw new HttpResponseException(HttpStatus.CONFLICT.value(), "This Preset Name is been used");

        dicePresetsRepository.findById(presetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + presetName, new Throwable("Type: " + DicePreset.class.getName())));

        dicePresetsRepository.deleteById(presetName);

        createPreset(newPresetName, dices);

        return dicePresetsRepository.findById(newPresetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + newPresetName, new Throwable("Type: " + DicePreset.class.getName())));
    }

    public void deletePreset(String presetName) {
        dicePresetsRepository.findById(presetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + presetName, new Throwable("Type: " + DicePreset.class.getName())));
        dicePresetsRepository.deleteById(presetName);
    }
}
