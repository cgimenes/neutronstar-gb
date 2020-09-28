package com.gimenes.neutronstargb.hardware.cpu.flags;

import com.gimenes.neutronstargb.hardware.cpu.registers.Register8;
import java.util.List;

public class Flags {
    private Register8 register;

    public Flags(Register8 register) {
        this.register = register;
    }

    public void set(FlagKind flag) {
        switch (flag) {
            case Z -> register.set((byte) (register.get() | 1 << 7));
            case N -> register.set((byte) (register.get() | 1 << 6));
            case H -> register.set((byte) (register.get() | 1 << 5));
            case C -> register.set((byte) (register.get() | 1 << 4));
        }
    }

    public void reset(FlagKind flag) {
        switch (flag) {
            case Z -> register.set((byte) (register.get() & ~(1 << 7)));
            case N -> register.set((byte) (register.get() & ~(1 << 6)));
            case H -> register.set((byte) (register.get() & ~(1 << 5)));
            case C -> register.set((byte) (register.get() & ~(1 << 4)));
        }
    }

    public void reset(Iterable<FlagKind> flags) {
        flags.forEach(this::reset);
    }

    public void resetAll() {
        reset(List.of(FlagKind.Z, FlagKind.N, FlagKind.H, FlagKind.C));
    }

    public byte get(FlagKind flag) {
        return switch (flag) {
            case Z -> (byte) (register.get() >> 7 & 1);
            case N -> (byte) (register.get() << 1 >> 7 & 1);
            case H -> (byte) (register.get() << 2 >> 7 & 1);
            case C -> (byte) (register.get() << 3 >> 7 & 1);
        };
    }
}
