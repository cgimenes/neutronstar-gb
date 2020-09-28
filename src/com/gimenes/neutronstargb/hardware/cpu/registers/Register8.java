package com.gimenes.neutronstargb.hardware.cpu.registers;

public class Register8 {
    private byte register;

    public byte get() {
        return register;
    }

    public void set(byte value) {
        this.register = value;
    }
}
