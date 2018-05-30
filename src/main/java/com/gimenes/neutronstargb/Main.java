package com.gimenes.neutronstargb;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] data = Main.class.getClassLoader().getResourceAsStream("DMG_ROM.bin").readAllBytes();
        System.out.printf(Integer.toHexString(data[0]));

//        CPU cpu = new CPU();
//        cpu.go(0x06, 0xEE);
    }
}
