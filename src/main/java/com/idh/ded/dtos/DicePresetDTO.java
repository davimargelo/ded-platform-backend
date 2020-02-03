package com.idh.ded.dtos;

import com.idh.ded.domain.Dice;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DicePresetDTO {

    @NotEmpty(message = "Preset needs a name")
    private String name;

    @Size(min = 1, message = "Dice List must have at least 1 dice")
    private List<Dice> diceList = new ArrayList<>();

    public DicePresetDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }
}
