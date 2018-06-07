package com.gimenes.neutronstargb;

import java.nio.ByteBuffer;

public class BinaryHelper {
    public static short wordFromBytes(byte high, byte low) {
        return ByteBuffer.wrap(new byte[]{high, low}).getShort();
    }

    public static byte getBit(byte b, byte position) {
        return (byte) ((b >> position) & 1);
    }
}
