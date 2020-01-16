package com.idh.ded.services;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class DiceService {


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
            sum += value   ;
        }
        result.put(rollResults.toString().replace(","," +"), sum);
        return result;
    }
}
