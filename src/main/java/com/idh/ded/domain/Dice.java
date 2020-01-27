package com.idh.ded.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idh.ded.domain.enums.DiceType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Dice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer d;

    @NotNull
    private Integer rolls;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "diceList")
    private List<DicePreset> dicePresetList = new ArrayList<>();

    public Dice() {
    }

    public Dice(DiceType d, int nOfRolls) {
        this.d = d.getCod();
        this.rolls = nOfRolls;
    }

    public DiceType getD() {
        return DiceType.toEnum(d);
    }

    public void setD(DiceType d) {
        this.d = d.getCod();
    }

    public int getRolls() {
        return rolls;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DicePreset> getDicePresetList() {
        return dicePresetList;
    }

    public void setDicePresetList(List<DicePreset> dicePresetList) {
        this.dicePresetList = dicePresetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return d.equals(dice.d) &&
                rolls.equals(dice.rolls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(d, rolls);
    }
}
