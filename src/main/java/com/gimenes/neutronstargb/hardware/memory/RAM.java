package com.gimenes.neutronstargb.hardware.memory;

public class RAM extends ROM {
    public RAM(byte size) {
        super(new byte[size]);
    }

    public void set(byte[] data) {
        System.arraycopy(data, 0, this.addresses, 0, data.length);
    }

    public void set(byte address, byte value) {
        this.addresses[address] = value;
    }
}
