package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.WordHelper;
import com.gimenes.neutronstargb.hardware.cpu.flags.FlagKind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16Kind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register8Kind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Registers;
import com.gimenes.neutronstargb.hardware.memory.MMU;

public class CPU {
    private final Registers registers;
    private final Stack stack;
    private final Timer timer;
    private final MMU mmu;

    public CPU() {
        // TODO injetar
        registers = new Registers();
        stack = new Stack();
        timer = new Timer();
        mmu = new MMU();
    }

    public void init() {
//        while (true) {
        tick();
        tick();
        tick();
        tick();
//        }
    }

    private void tick() {
        // Fetch the next opcode.
        byte b = fetch();
        // Decode the fetched opcode.
        switch (b) {
            case 0x01:
                LDn16(Register16Kind.BC);
                break;
            case 0x11:
                LDn16(Register16Kind.DE);
                break;
            case 0x21:
                LDn16(Register16Kind.HL);
                break;
            case 0x31:
                LDn16(Register16Kind.SP);
                break;
            case 0x32:
                LDD16n();
            case 0x0B:
                DEC16(Register16Kind.BC);
                break;
            case 0x1B:
                DEC16(Register16Kind.DE);
                break;
            case 0x2B:
                DEC16(Register16Kind.HL);
                break;
            case 0x3B:
                DEC16(Register16Kind.SP);
                break;
            case (byte) 0xA8:
                XORn(Register8Kind.B);
                break;
            case (byte) 0xA9:
                XORn(Register8Kind.C);
                break;
            case (byte) 0xAA:
                XORn(Register8Kind.D);
                break;
            case (byte) 0xAB:
                XORn(Register8Kind.E);
                break;
            case (byte) 0xAC:
                XORn(Register8Kind.H);
                break;
            case (byte) 0xAD:
                XORn(Register8Kind.L);
                break;
            case (byte) 0xAF:
                XORn(Register8Kind.A);
                break;
        }

        // Fetch any extra data required to resolve the operation including extra opcodes and literals.
        // Record all m-cycles consumed in the operation so that we can block later to adjust our timings.
        // Execute the opcode.
    }

    private byte fetch() {
        Register16 pc = registers.get(Register16Kind.PC);
        byte b = mmu.get(pc.get());
        pc.set((short) (pc.get() + 0x1));
        return b;
    }


    private short fetch16() {
        // Little-endian
        byte l = fetch();
        byte h = fetch();

        return WordHelper.wordFromBytes(h, l);
    }

    private void LDn16(Register16Kind register) {
        registers.get(register).set(fetch16());
    }

    private void XORn(Register8Kind register) {
        byte xor = (byte) (registers.get(Register8Kind.A).get() ^ registers.get(register).get());
        registers.get(Register8Kind.A).set(xor);
        registers.getFlags().resetAll();
        if (xor == 0x0) {
            registers.getFlags().set(FlagKind.Z);
        }
    }

    private void DEC16(Register16Kind register) {
        registers.get(register).set((short) (registers.get(register).get() - 0x1));
    }

    private void LDD16n() {
        // TODO migrar esse load para uma chamada de outra instrução
        mmu.set(registers.get(Register16Kind.HL).get(), registers.get(Register8Kind.A).get());
        DEC16(Register16Kind.HL);
    }

    private void LDDn16() {

    }
}
