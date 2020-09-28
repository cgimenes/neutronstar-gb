package com.gimenes.neutronstargb.hardware.memory;

public class MMU {
    private final ROM bios;
    private final RAM wram;
    private final RAM vram;
    private final ROM cartridge;
    private final RAM internalRam;

    public MMU(byte[] cartridge) {
        // TODO injetar
        bios = new ROM(BootROM.GAMEBOY_CLASSIC);
        wram = new RAM((short) 0x2000);
        vram = new RAM((short) 0x2000);
        internalRam = new RAM((short) 0x7E);
        this.cartridge = new ROM(cartridge);
    }

    public byte get(short address) {
        if (((address & 0xF000) == 0x8000) || ((address & 0xF000) == 0x9000)) { // 0x8000 -> 0x9FFF
            return vram.get((short) (address & 0x1FFF));
        }
        if ((address & 0xFF80) == 0xFF80) { // 0xFF80 -> 0xFFFE
            return internalRam.get((short) (address & 0x007F));
        }
        if ((address & 0xFF00) == 0x0000) { // 0x0000 -> 0x00FF
            return bios.get(address);
        }
        return cartridge.get(address);
    }

    public void set(short address, byte data) {
        if (((address & 0xF000) == 0x8000) || ((address & 0xF000) == 0x9000)) {
            vram.set((short) (address & 0x1FFF), data);
            return;
        }
        if ((address & 0xFF80) == 0xFF80) {
            internalRam.set((short) (address & 0x007F), data);
        }
    }
}
