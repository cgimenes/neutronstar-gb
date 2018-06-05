package com.gimenes.neutronstargb.hardware.cpu.registers;

import com.gimenes.neutronstargb.hardware.cpu.flags.Flags;
import java.util.EnumSet;
import java.util.HashMap;

public class Registers {
    private final HashMap<Register8Kind, Register8> registers8;
    private final HashMap<Register16Kind, Register16> registers16;
    private final Flags flags;

    public Registers() {
        registers8 = new HashMap<>();
        registers16 = new HashMap<>();

        // 8-bit
        EnumSet.allOf(Register8Kind.class).forEach(kind -> registers8.put(kind, new Register8()));

        // 16-bit
        Register16Kind.getAllNotPaired().forEach(kind -> registers16.put(kind, new Register16()));

        // paired 16-bit
        registers16.put(Register16Kind.AF, new Register16Paired(this.get(Register8Kind.A), this.get(Register8Kind.F)));
        registers16.put(Register16Kind.BC, new Register16Paired(this.get(Register8Kind.B), this.get(Register8Kind.C)));
        registers16.put(Register16Kind.DE, new Register16Paired(this.get(Register8Kind.D), this.get(Register8Kind.E)));
        registers16.put(Register16Kind.HL, new Register16Paired(this.get(Register8Kind.H), this.get(Register8Kind.L)));

        // flags
        flags = new Flags(this.get(Register8Kind.F));
    }

    public Flags getFlags() {
        return flags;
    }

    public Register8 get(Register8Kind register) {
        return registers8.get(register);
    }

    public Register16 get(Register16Kind register) {
        return registers16.get(register);
    }
}
