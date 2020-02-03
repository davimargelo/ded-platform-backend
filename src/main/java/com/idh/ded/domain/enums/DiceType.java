package com.idh.ded.domain.enums;

import com.idh.ded.services.exceptions.ObjectNotFoundException;

public enum DiceType {
    D4(4, "Rolls D4"),
    D6(6, "Rolls D6"),
    D8(8, "Rolls D8"),
    D10(10, "Rolls D10"),
    D12(12, "Rolls D12"),
    D20(20, "Rolls D20"),
    D100(100, "Rolls D100");

    private int cod;
    private String description;

    private DiceType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static DiceType toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (DiceType x : DiceType.values())
            if ((cod == x.getCod()))
                return x;

        throw new ObjectNotFoundException("Invalid dice - number os sides: " + cod);
    }
}
