package com.idh.ded.services;

import com.idh.ded.domain.Dice;
import com.idh.ded.domain.DicePreset;
import com.idh.ded.domain.enums.DiceType;
import com.idh.ded.repositories.DicePresetsRepository;
import com.idh.ded.repositories.DiceRollRepository;
import com.idh.ded.services.exceptions.ObjectAlreadyExistsException;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiceService {

    @Autowired
    DicePresetsRepository dicePresetsRepository;

    @Autowired
    DiceRollRepository diceRollRepository;

    public Page<DicePreset> getAllDicePresetsByPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return dicePresetsRepository.findAll(pageRequest);
    }

    public Map<String, String> roll(Integer dice, Integer rolls) {
        Map<String, String> result = new HashMap<>();
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
        result.put("sum", rollResults.toString()
                .replace(",", " +")
                .replace("[", "")
                .replace("]", ""));
        result.put("result", String.valueOf((sum)));
        return result;
    }

    public List<Map<String, String>> rollPreset(String presetName) {
        DicePreset preset = findOne(presetName);

        List<Map<String, String>> result = new ArrayList<>();

        result = preset.getDiceList().stream().map(dice -> roll(dice.getD().getCod(), dice.getRolls())).collect(Collectors.toList());
// foreach para referencia
//        for (Dice dice : preset.getDiceList()) {
//            result.add(roll(dice.getD().getCod(), dice.getRolls()));
//        }
        return result;
    }

    public DicePreset createPreset(String presetName, List<Map<String, Integer>> dices) {

        existsById(presetName);

        List<Dice> diceList = new ArrayList<>();

        for (Map<String, Integer> dice : dices) {
            diceList.add(diceProducer(DiceType.toEnum(dice.get("d")), dice.get("rolls")));
        }

        DicePreset dicePreset = new DicePreset(presetName);

        dicePreset.getDiceList().addAll(diceList);

        for (Dice d : diceList) {
            d.getDicePresetList().add(dicePreset);
        }

        dicePresetsRepository.save(dicePreset);

        return dicePresetsRepository.getOne(presetName);
    }

    public DicePreset findOne(String presetName) {
        return dicePresetsRepository.findById(presetName)
                .orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + presetName, new Throwable("Type: " + DicePreset.class.getName())));
    }

    private boolean existsById(String presetName) {
        if (dicePresetsRepository.existsById(presetName))
            throw new ObjectAlreadyExistsException("This Preset Name is been used");
        else
            return false;
    }

    public List<DicePreset> getAllDicePresets() {
        return dicePresetsRepository.findAll();
    }

    public DicePreset updatePreset(String presetName, String newPresetName, List<Map<String, Integer>> dices) {
        existsById(newPresetName);

        findOne(presetName);

        dicePresetsRepository.deleteById(presetName);

        createPreset(newPresetName, dices);

        return findOne(newPresetName);
    }

    public DicePreset updatePresetDicelist(String presetName, List<Map<String, Integer>> dices) {
        findOne(presetName);

        DicePreset dicePresetToUpdate = dicePresetsRepository.getOne(presetName);

        List<Dice> diceList = new ArrayList<>();

        for (Map<String, Integer> dice : dices) {
            diceList.add(diceProducer(DiceType.toEnum(dice.get("d")), dice.get("rolls")));
        }

        dicePresetToUpdate.getDiceList().clear();
        dicePresetToUpdate.getDiceList().addAll(diceList);

        dicePresetsRepository.save(dicePresetToUpdate);
        return dicePresetsRepository.getOne(presetName);
    }

    public void deletePreset(String presetName) {
        findOne(presetName);
        dicePresetsRepository.deleteById(presetName);
    }

    private Dice diceProducer(DiceType diceType, Integer rolls) {
        List<Dice> dicesAndRolls = diceRollRepository.findAll();

        for (Dice d : dicesAndRolls) {
            if (d.getD() == diceType && d.getRolls() == rolls)
                return d;
        }
        Dice dice = new Dice(diceType, rolls);
        diceRollRepository.save(dice);

        return dice;
    }

    public List<Dice> findRollers() {
        return diceRollRepository.findAll();
    }
}
