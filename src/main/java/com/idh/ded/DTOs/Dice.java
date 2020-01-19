package com.idh.ded.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private int d;

    @NotNull
    private int rolls;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "diceList")
    private List<DicePreset> dicePresetList = new ArrayList<>();

    public Dice() {
    }

    public Dice(int d, int nOfRolls) {
        this.d = d;
        this.rolls = nOfRolls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getRolls() {
        return rolls;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public List<DicePreset> getDicePresetList() {
        return dicePresetList;
    }

    public void setDicePresetList(List<DicePreset> dicePresetList) {
        this.dicePresetList = dicePresetList;
    }
}
