package com.idh.ded.DTOs;

public class Dice {
    int nOfDices;
    int nOfRolls;

    public Dice() {
    }

    public Dice(int nOfSides, int nOfDices, int nOfRolls) {
        this.nOfDices = nOfDices;
        this.nOfRolls = nOfRolls;
    }

    public int getnOfDices() {
        return nOfDices;
    }

    public void setnOfDices(int nOfDices) {
        this.nOfDices = nOfDices;
    }

    public int getnOfRolls() {
        return nOfRolls;
    }

    public void setnOfRolls(int nOfRolls) {
        this.nOfRolls = nOfRolls;
    }
}
