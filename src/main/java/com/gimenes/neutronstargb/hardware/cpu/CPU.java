package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.hardware.cpu.flags.FlagKind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16Kind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register8Kind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Registers;
import com.gimenes.neutronstargb.hardware.memory.ROM;
import java.io.IOException;

public class CPU {
    private final ROM bootRom;
    private final Registers registers;
    private final Stack stack;
    private final Timer timer;

    public CPU() {
        bootRom = getBootRom();
        registers = new Registers();
        stack = new Stack();
        timer = new Timer();
    }

    public void init() {
//        while (true) {
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
                LD16(Register16Kind.BC);
                break;
            case 0x11:
                LD16(Register16Kind.DE);
                break;
            case 0x21:
                LD16(Register16Kind.HL);
                break;
            case 0x31:
                LD16(Register16Kind.SP);
                break;
            case (byte) 0xAF:
                XOR();
                break;
        }

        // Fetch any extra data required to resolve the operation including extra opcodes and literals.
        // Record all m-cycles consumed in the operation so that we can block later to adjust our timings.
        // Execute the opcode.
    }

    private byte fetch() {
        Register16 pc = registers.get(Register16Kind.PC);
        byte b = bootRom.get(pc.get());
        pc.set((short) (pc.get() + 0x1));
        return b;
    }

    private ROM getBootRom() {
        try {
            byte[] data = this.getClass().getClassLoader().getResourceAsStream("DMG_ROM.bin").readAllBytes();
            return new ROM(data);
        } catch (IOException ignored) {
            return null;
        }
    }

    private void LD16(Register16Kind register) {
        byte l = fetch();
        byte h = fetch();

        registers.get(register).set((short) ((h << 8) | l));
    }

    private void XOR() {
        byte xor = (byte) (registers.get(Register8Kind.A).get() ^ registers.get(Register8Kind.A).get());
        registers.get(Register8Kind.A).set(xor);
        registers.getFlags().reset(FlagKind.Z);
        if (xor == 0x0) {
            registers.getFlags().set(FlagKind.Z);
        }
        registers.getFlags().reset(FlagKind.N);
        registers.getFlags().reset(FlagKind.H);
        registers.getFlags().reset(FlagKind.C);
    }
}
