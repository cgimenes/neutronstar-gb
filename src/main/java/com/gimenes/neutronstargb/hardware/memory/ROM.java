package com.gimenes.neutronstargb.hardware.memory;

public class ROM {
    protected final byte[] addresses;

    public ROM(byte[] data) {
        this.addresses = data;
    }

    public byte get(short address) {
        return this.addresses[address];
    }
}
