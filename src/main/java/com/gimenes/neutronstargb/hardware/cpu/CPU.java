package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.hardware.memory.ROM;

public class CPU {
    private final ROM bootRom;
    private final Registers registers;
    private final Stack stack;

    public CPU() {
        byte[] data = this.getClass().getClassLoader().getResourceAsStream("DMG_ROM.bin").readAllBytes();
        bootRom = new ROM(data);
        registers = new Registers();
        stack = new Stack();
    }

    public void tick() {

    }
}
