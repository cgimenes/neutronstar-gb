package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.hardware.cpu.flags.Flags;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16Kind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Registers;
import com.gimenes.neutronstargb.hardware.memory.ROM;
import java.io.IOException;

public class CPU {
    private final ROM bootRom;
    private final Registers registers;
    private final Stack stack;
    private final Flags flags;

    public CPU() {
        bootRom = getBootRom();
        registers = new Registers();
        stack = new Stack();
        flags = new Flags();
    }

    public void tick() {
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
