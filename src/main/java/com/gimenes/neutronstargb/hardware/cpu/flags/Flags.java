package com.gimenes.neutronstargb.hardware.cpu.flags;

public class Flags {
    private byte register;

    public Flags() {
        // initialize flags
        register = 0x0;
    }

    public void set(FlagKind flag) {
        switch (flag) {
            case Z:
                register = (byte) (register | 1 << 7);
                break;
            case N:
                register = (byte) (register | 1 << 6);
                break;
            case H:
                register = (byte) (register | 1 << 5);
                break;
            case C:
                register = (byte) (register | 1 << 4);
                break;
        }
    }

    public void reset(FlagKind flag) {
         switch (flag) {
            case Z:
                register = (byte) (register & ~(1 << 7));
                break;
            case N:
                register = (byte) (register & ~(1 << 6));
                break;
            case H:
                register = (byte) (register & ~(1 << 5));
                break;
            case C:
                register = (byte) (register & ~(1 << 4));
                break;
        }
    }

    public byte get(FlagKind flag) {
        switch (flag) {
            case Z:
                return (byte) (register >> 7);
            case N:
                return (byte) (register << 1 >> 6);
            case H:
                return (byte) (register << 2 >> 5);
            case C:
                return (byte) (register << 3 >> 4);
        }
        throw new RuntimeException(String.format(
                "FlagKind '%s', not registered at '%s' class",
                flag.toString(),
                this.getClass().getSimpleName()));
    }
}
