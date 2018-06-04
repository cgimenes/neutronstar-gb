package com.gimenes.neutronstargb.hardware.cpu.flags;

import com.gimenes.neutronstargb.hardware.cpu.registers.Register8;

public class Flags {
    private Register8 register;

    public Flags(Register8 register) {
        this.register = register;

        // initialize flags
        register.set((byte) 0x0);
    }

    public void set(FlagKind flag) {
        switch (flag) {
            case Z:
                register.set((byte) (register.get() | 1 << 7));
                break;
            case N:
                register.set((byte) (register.get() | 1 << 6));
                break;
            case H:
                register.set((byte) (register.get() | 1 << 5));
                break;
            case C:
                register.set((byte) (register.get() | 1 << 4));
                break;
        }
    }

    public void reset(FlagKind flag) {
         switch (flag) {
            case Z:
                register.set((byte) (register.get() & ~(1 << 7)));
                break;
            case N:
                register.set((byte) (register.get() & ~(1 << 6)));
                break;
            case H:
                register.set((byte) (register.get() & ~(1 << 5)));
                break;
            case C:
                register.set((byte) (register.get() & ~(1 << 4)));
                break;
        }
    }

    public byte get(FlagKind flag) {
        switch (flag) {
            case Z:
                return (byte) (register.get() >> 7);
            case N:
                return (byte) (register.get() << 1 >> 6);
            case H:
                return (byte) (register.get() << 2 >> 5);
            case C:
                return (byte) (register.get() << 3 >> 4);
        }
        throw new RuntimeException(String.format(
                "FlagKind '%s', not registered at '%s' class",
                flag.toString(),
                this.getClass().getSimpleName()));
    }
}