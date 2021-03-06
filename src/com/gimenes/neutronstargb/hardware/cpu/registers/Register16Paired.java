package com.gimenes.neutronstargb.hardware.cpu.registers;

import com.gimenes.neutronstargb.BinaryHelper;

public class Register16Paired extends Register16 {
    private final Register8 high;
    private final Register8 low;

    public Register16Paired(Register8 high, Register8 low) {
        this.high = high;
        this.low = low;
    }

    public short get() {
        return BinaryHelper.wordFromBytes(high.get(), low.get());
    }

    public void set(short value) {
        high.set((byte) (value >> 8));
        low.set((byte) value);
    }
}
