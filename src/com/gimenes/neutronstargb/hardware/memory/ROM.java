package com.gimenes.neutronstargb.hardware.memory;

class ROM {
    protected final byte[] addresses;

    public ROM(byte[] data) {
        this.addresses = data;
    }

    public ROM(int[] data) {
        byte[] bytes = new byte[data.length];
        for(int i = 0; i < data.length; i++) {
            bytes[i] = (byte) data[i];
        }
        this.addresses = bytes;
    }

    public byte get(short address) {
        return this.addresses[address];
    }
}
