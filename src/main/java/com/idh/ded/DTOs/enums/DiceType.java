package com.idh.ded.DTOs.enums;

public enum DiceType {
        D3(4, "Rolls D4"),
        D4(6, "Rolls D6"),
        D8(8, "Rolls D8"),
        D10(10, "Rolls D10"),
        D12(12, "Rolls D12"),
        D20(20, "Rolls D20");

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

            throw new IllegalArgumentException("Invalid Id: " + cod);
        }
}
