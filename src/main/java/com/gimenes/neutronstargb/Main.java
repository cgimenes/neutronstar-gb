package com.gimenes.neutronstargb;

import com.gimenes.neutronstargb.hardware.cpu.CPU;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.tick();
    }
}
