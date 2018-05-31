package com.gimenes.neutronstargb.hardware.cpu;

public class Register16Paired extends Register16 {
    private Register8 high;
    private Register8 low;

    public Register16Paired(Register8 high, Register8 low) {
        this.high = high;
        this.low = low;
    }

    public short getValue() {
        return (short) ((high.getValue() << 8) | low.getValue());
    }

    public void setValue(short value) {
        high.setValue((byte) (value >> 8));
        low.setValue((byte) (value << 8 >> 8));
    }
}
