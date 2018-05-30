package com.gimenes.neutronstargb;

import com.gimenes.neutronstargb.hardware.cpu.CPU;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CPU cpu = new CPU();
        while (true) {
            cpu.tick();
        }
    }
}
