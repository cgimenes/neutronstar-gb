package com.gimenes.neutronstargb;

import com.gimenes.neutronstargb.hardware.cpu.CPU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] cartridge = Files.readAllBytes(Paths.get("/home/gimenes/Downloads/dmg_sound/dmg_sound.gb"));
        CPU cpu = new CPU(cartridge);
        cpu.init();
    }
}
