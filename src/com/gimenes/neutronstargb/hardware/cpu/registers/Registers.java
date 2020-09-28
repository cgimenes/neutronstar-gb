package com.gimenes.neutronstargb.hardware.cpu.registers;

import com.gimenes.neutronstargb.hardware.cpu.flags.Flags;

public class Registers {
    // 8-bit high
    public Register8 A; // accumulator
    public Register8 B, D, H;
    // 8-bit low
    public Register8 F; // flags
    public Register8 C, E, L;

    // 16-bit
    public Register16 SP; // stack pointer
    public Register16 PC; // program counter
    // 16-bit paired
    public Register16 AF, BC, DE, HL;

    // flags
    public final Flags Flags;

    public Registers() {
        // 8-bit
        A = new Register8();
        B = new Register8();
        D = new Register8();
        H = new Register8();
        F = new Register8();
        C = new Register8();
        E = new Register8();
        L = new Register8();

        // 16-bit
        SP = new Register16();
        PC = new Register16();

        // paired 16-bit
        AF = new Register16Paired(A, F);
        BC = new Register16Paired(B, C);
        DE = new Register16Paired(D, E);
        HL = new Register16Paired(H, L);

        // flags
        Flags = new Flags(F);
    }
}
