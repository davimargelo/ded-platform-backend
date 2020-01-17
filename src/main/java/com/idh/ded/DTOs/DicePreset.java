package com.idh.ded.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DicePreset {

    @Id
    @NotNull
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "DICEPRESET_DICE", joinColumns = @JoinColumn(name = "dicePreset_id"), inverseJoinColumns = @JoinColumn(name = "dice_id"))
    private List<Dice> diceList = new ArrayList<>();

    public DicePreset() {
    }

    public DicePreset(String name) {
        this.name = name;
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
