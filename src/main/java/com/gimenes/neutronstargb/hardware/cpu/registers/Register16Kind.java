package com.gimenes.neutronstargb.hardware.cpu.registers;

import java.util.Arrays;

public enum Register16Kind {
    // stack pointer
    SP,

    // program counter
    PC,

    // paired
    AF, BC, DE, HL;

    public static Iterable<Register16Kind> getAllNotPaired() {
        return Arrays.asList(SP, PC);
    }
}
