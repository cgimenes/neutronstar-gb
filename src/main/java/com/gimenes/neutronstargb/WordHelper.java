package com.gimenes.neutronstargb;

import java.nio.ByteBuffer;

public class WordHelper {
    public static short wordFromBytes(byte high, byte low) {
        return ByteBuffer.wrap(new byte[]{high, low}).getShort();
    }
}
