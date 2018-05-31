package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.hardware.memory.ROM;
import java.io.IOException;

public class CPU {
    private final ROM bootRom;
    private final Registers registers;
    private final Stack stack;

    public CPU() {
        bootRom = getBootRom();
        registers = new Registers();
        stack = new Stack();
    }

    public void tick() {
        registers.get(Register16Kind.AF).setValue((short) 0x1234);
        assert registers.get(Register8Kind.A).getValue() == 0x12;
        assert registers.get(Register8Kind.F).getValue() == 0x34;
    }

    private ROM getBootRom() {
        try {
            byte[] data = this.getClass().getClassLoader().getResourceAsStream("DMG_ROM.bin").readAllBytes();
            return new ROM(data);
        } catch (IOException ignored) {
            return null;
        }
    }
}
