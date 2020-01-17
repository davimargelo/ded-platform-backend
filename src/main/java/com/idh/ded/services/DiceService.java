package com.idh.ded.services;

import com.idh.ded.DTOs.Dice;
import com.idh.ded.DTOs.DicePreset;
import com.idh.ded.repositories.DicePresetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

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

    public void createPreset(String presetName, List<Dice> dices) {

        DicePreset dicePreset = new DicePreset(presetName);

        dicePreset.getDiceList().addAll(dices);

        for (Dice d : dices) {
            d.getDicePresetList().add(dicePreset);
        }

        dicePresetsRepository.save(dicePreset);
    }

}
