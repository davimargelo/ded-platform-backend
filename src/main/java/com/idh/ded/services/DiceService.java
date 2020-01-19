package com.idh.ded.services;

import com.idh.ded.DTOs.Dice;
import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.repositories.DicePresetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpResponseException;

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
                .replace(","," +")
                .replace("[","")
                .replace("]",""), sum);
        return result;
    }

    public DicePreset createPreset(String presetName, List<Dice> dices) throws HttpResponseException {

        if (dicePresetsRepository.existsById(presetName))
            throw new HttpResponseException(HttpStatus.CONFLICT.value(), "This Preset Name is been used");

        DicePreset dicePreset = new DicePreset(presetName);

        dicePreset.getDiceList().addAll(dices);

        for (Dice d : dices) {
            d.getDicePresetList().add(dicePreset);
        }

        dicePresetsRepository.save(dicePreset);

        return dicePreset;
    }

    public List<DicePreset> getAllDicePresets() {
        return dicePresetsRepository.findAll();
    }

}
