package com.gimenes.neutronstargb.hardware.cpu;

public class CPU {
    private Registers registers = new Registers();

    public void go(int op, int data) {

        if (op == 0x06) {
//            registers.setB((byte) data);
        }
    }
}
