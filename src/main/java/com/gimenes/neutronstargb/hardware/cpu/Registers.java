package com.gimenes.neutronstargb.hardware.cpu;

import java.util.HashMap;

public class Registers {
    private HashMap<Register8Kind, Register8> registers8;
    private HashMap<Register16Kind, Register16> registers16;

    public Registers() {
        registers8 = new HashMap<>();
        registers16 = new HashMap<>();

        // high
        registers8.put(Register8Kind.A, new Register8());
        registers8.put(Register8Kind.B, new Register8());
        registers8.put(Register8Kind.D, new Register8());
        registers8.put(Register8Kind.H, new Register8());

        // low
        registers8.put(Register8Kind.F, new Register8());
        registers8.put(Register8Kind.C, new Register8());
        registers8.put(Register8Kind.E, new Register8());
        registers8.put(Register8Kind.L, new Register8());

        // 16-bit
        registers16.put(Register16Kind.SP, new Register16());
        registers16.put(Register16Kind.PC, new Register16());
        //paired
        registers16.put(Register16Kind.AF, new Register16Paired(this.get(Register8Kind.A), this.get(Register8Kind.F)));
        registers16.put(Register16Kind.BC, new Register16Paired(this.get(Register8Kind.B), this.get(Register8Kind.C)));
        registers16.put(Register16Kind.DE, new Register16Paired(this.get(Register8Kind.D), this.get(Register8Kind.E)));
        registers16.put(Register16Kind.HL, new Register16Paired(this.get(Register8Kind.H), this.get(Register8Kind.L)));
    }

    public Register8 get(Register8Kind register) {
        return registers8.get(register);
    }

    public Register16 get(Register16Kind register) {
        return registers16.get(register);
    }
}
