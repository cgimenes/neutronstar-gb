package com.gimenes.neutronstargb.hardware.memory;

import java.io.IOException;

public class MMU {
    private final ROM bios;
    private final RAM wram;
    private final RAM vram;

    public MMU() {
        // TODO injetar
        bios = getBios();
        wram = new RAM((short) 0x2000);
        vram = new RAM((short) 0x2000);
    }

    private ROM getBios() {
        try {
            byte[] data = this.getClass().getClassLoader().getResourceAsStream("DMG_ROM.bin").readAllBytes();
            return new ROM(data);
        } catch (IOException ignored) {
            return null;
        }
    }

    public byte get(short address) {
        if (((address & 0xF000) == 0x8000) || ((address & 0xF000) == 0x9000)) {
            return vram.get((short) (address & 0x1FFF));
        }
        return bios.get(address);
    }

    public void set(short address, byte data) {
        if (((address & 0xF000) == 0x8000) || ((address & 0xF000) == 0x9000)) {
            vram.set((short) (address & 0x1FFF), data);
        }
    }


}
