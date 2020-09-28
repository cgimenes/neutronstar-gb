package com.gimenes.neutronstargb.hardware.memory;

import java.util.Random;

class RAM extends ROM {
    private final Random rand = new Random();

    public RAM(short size) {
        super(new byte[size]);
        rand.nextBytes(this.addresses);
    }

    public void init(byte[] data) {
        System.arraycopy(data, 0, this.addresses, 0, data.length);
    }

    public void set(short address, byte value) {
        this.addresses[address] = value;
    }
}
