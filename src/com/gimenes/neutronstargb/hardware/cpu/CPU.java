package com.gimenes.neutronstargb.hardware.cpu;

import com.gimenes.neutronstargb.BinaryHelper;
import com.gimenes.neutronstargb.hardware.cpu.flags.FlagKind;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register16;
import com.gimenes.neutronstargb.hardware.cpu.registers.Register8;
import com.gimenes.neutronstargb.hardware.cpu.registers.Registers;
import com.gimenes.neutronstargb.hardware.memory.MMU;

public class CPU {
    private final Registers registers;
    private final Timer timer;
    private final MMU mmu;

    public CPU(byte[] cartridge) {
        // TODO injetar
        registers = new Registers();
        timer = new Timer();
        mmu = new MMU(cartridge);
//        mmu.get((short) 0xA000);
    }

    public void init() {
        while (true) {
            tick();
        }
    }

    private void tick() {
        // Fetch the next opcode.
        byte b = fetch();
        // Decode the fetched opcode.
        switch (b) {
            case 0x06 -> LD8(registers.B, fetch());
            case 0x0E -> LD8(registers.C, fetch());
            case 0x16 -> LD8(registers.D, fetch());
            case 0x1E -> LD8(registers.E, fetch());
            case 0x26 -> LD8(registers.H, fetch());
            case 0x2E -> LD8(registers.L, fetch());
            case 0x3E -> LD8(registers.A, fetch());
            case 0x20 -> JRcc(FlagKind.Z, (byte) 0x0, fetch());
            case 0x28 -> JRcc(FlagKind.Z, (byte) 0x1, fetch());
            case 0x30 -> JRcc(FlagKind.C, (byte) 0x0, fetch());
            case 0x38 -> JRcc(FlagKind.C, (byte) 0x1, fetch());
            case 0x01 -> LD16(registers.BC, fetch16());
            case 0x11 -> LD16(registers.DE, fetch16());
            case 0x21 -> LD16(registers.HL, fetch16());
            case 0x31 -> LD16(registers.SP, fetch16());
            case 0x32 -> LDD16n();
            case 0x4F -> LDnA(registers.C);
            case 0x77 -> LDnA(registers.HL);
            case 0x1A -> LDAn(registers.DE);
            case 0x05 -> DEC(registers.B);
            case 0x0B -> DEC(registers.BC);
            case 0x1B -> DEC(registers.DE);
            case 0x2B -> DEC(registers.HL);
            case 0x3B -> DEC(registers.SP);
            case 0x0C -> INC(registers.C);
            case 0x17 -> RLA();
            case (byte) 0xC1 -> POP(registers.BC);
            case (byte) 0xC5 -> PUSH(registers.BC);
            case (byte) 0xCD -> CALL();
            case (byte) 0xA8 -> XORn(registers.B);
            case (byte) 0xA9 -> XORn(registers.C);
            case (byte) 0xAA -> XORn(registers.D);
            case (byte) 0xAB -> XORn(registers.E);
            case (byte) 0xAC -> XORn(registers.H);
            case (byte) 0xAD -> XORn(registers.L);
            case (byte) 0xAF -> XORn(registers.A);
            case (byte) 0xCB -> CB();
            case (byte) 0xE0 -> LDHnA();
            case (byte) 0xE2 -> LDcA();
            default -> throw new RuntimeException(String.format(
                "OpCode: '%02X' - PC: '%02X'",
                b,
                registers.PC.get()));
        }

        // Fetch any extra data required to resolve the operation including extra opcodes and literals.
        // Record all m-cycles consumed in the operation so that we can block later to adjust our timings.
        // Execute the opcode.
    }

    private void CB() {
        byte b = fetch();
        switch (b) {
            case 0x40 -> BIT(registers.B, (byte) 0x0);
            case 0x41 -> BIT(registers.C, (byte) 0x0);
            case 0x42 -> BIT(registers.D, (byte) 0x0);
            case 0x43 -> BIT(registers.E, (byte) 0x0);
            case 0x44 -> BIT(registers.H, (byte) 0x0);
            case 0x45 -> BIT(registers.L, (byte) 0x0);
            case 0x47 -> BIT(registers.A, (byte) 0x0);
            case 0x48 -> BIT(registers.B, (byte) 0x1);
            case 0x49 -> BIT(registers.C, (byte) 0x1);
            case 0x4A -> BIT(registers.D, (byte) 0x1);
            case 0x4B -> BIT(registers.E, (byte) 0x1);
            case 0x4C -> BIT(registers.H, (byte) 0x1);
            case 0x4D -> BIT(registers.L, (byte) 0x1);
            case 0x4F -> BIT(registers.A, (byte) 0x1);
            case 0x50 -> BIT(registers.B, (byte) 0x2);
            case 0x51 -> BIT(registers.C, (byte) 0x2);
            case 0x52 -> BIT(registers.D, (byte) 0x2);
            case 0x53 -> BIT(registers.E, (byte) 0x2);
            case 0x54 -> BIT(registers.H, (byte) 0x2);
            case 0x55 -> BIT(registers.L, (byte) 0x2);
            case 0x57 -> BIT(registers.A, (byte) 0x2);
            case 0x58 -> BIT(registers.B, (byte) 0x3);
            case 0x59 -> BIT(registers.C, (byte) 0x3);
            case 0x5A -> BIT(registers.D, (byte) 0x3);
            case 0x5B -> BIT(registers.E, (byte) 0x3);
            case 0x5C -> BIT(registers.H, (byte) 0x3);
            case 0x5D -> BIT(registers.L, (byte) 0x3);
            case 0x5F -> BIT(registers.A, (byte) 0x3);
            case 0x60 -> BIT(registers.B, (byte) 0x4);
            case 0x61 -> BIT(registers.C, (byte) 0x4);
            case 0x62 -> BIT(registers.D, (byte) 0x4);
            case 0x63 -> BIT(registers.E, (byte) 0x4);
            case 0x64 -> BIT(registers.H, (byte) 0x4);
            case 0x65 -> BIT(registers.L, (byte) 0x4);
            case 0x67 -> BIT(registers.A, (byte) 0x4);
            case 0x68 -> BIT(registers.B, (byte) 0x5);
            case 0x69 -> BIT(registers.C, (byte) 0x5);
            case 0x6A -> BIT(registers.D, (byte) 0x5);
            case 0x6B -> BIT(registers.E, (byte) 0x5);
            case 0x6C -> BIT(registers.H, (byte) 0x5);
            case 0x6D -> BIT(registers.L, (byte) 0x5);
            case 0x6F -> BIT(registers.A, (byte) 0x5);
            case 0x70 -> BIT(registers.B, (byte) 0x6);
            case 0x71 -> BIT(registers.C, (byte) 0x6);
            case 0x72 -> BIT(registers.D, (byte) 0x6);
            case 0x73 -> BIT(registers.E, (byte) 0x6);
            case 0x74 -> BIT(registers.H, (byte) 0x6);
            case 0x75 -> BIT(registers.L, (byte) 0x6);
            case 0x77 -> BIT(registers.A, (byte) 0x6);
            case 0x78 -> BIT(registers.B, (byte) 0x7);
            case 0x79 -> BIT(registers.C, (byte) 0x7);
            case 0x7A -> BIT(registers.D, (byte) 0x7);
            case 0x7B -> BIT(registers.E, (byte) 0x7);
            case 0x7C -> BIT(registers.H, (byte) 0x7);
            case 0x7D -> BIT(registers.L, (byte) 0x7);
            case 0x7F -> BIT(registers.A, (byte) 0x7);
        }
    }

    private void JRcc(FlagKind flag, byte set, byte data) {
        if (registers.Flags.get(flag) == set) {
            registers.PC.set((short) (registers.PC.get() + data));
        }
    }

    private void BIT(Register8 register, byte bitPosition) {
        registers.Flags.reset(FlagKind.Z);
        if (BinaryHelper.getBit(register.get(), bitPosition) == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
        registers.Flags.reset(FlagKind.N);
        registers.Flags.set(FlagKind.H);
    }

    private byte fetch() {
        Register16 pc = registers.PC;
        byte b = mmu.get(pc.get());
        pc.set((short) (pc.get() + 0x1));
        return b;
    }


    private short fetch16() {
        // Little-endian
        byte l = fetch();
        byte h = fetch();

        return BinaryHelper.wordFromBytes(h, l);
    }

    private void LD16(Register16 register, short data) {
        register.set(data);
    }

    private void XORn(Register8 register) {
        byte xor = (byte) (registers.A.get() ^ register.get());
        registers.A.set(xor);
        registers.Flags.resetAll();
        if (xor == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
    }

    private void DEC(Register8 register) {
//        if ((register.get() & 0x0f) == 0x0f) {
//            registers.Flags.set(FlagKind.H);
//        }
        register.set((byte) (register.get() - 0x1));
        if (register.get() == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
        registers.Flags.set(FlagKind.N);
    }

    private void DEC(Register16 register) {
//        if ((register.get() & 0x0f) == 0x0f) {
//            registers.Flags.set(FlagKind.H);
//        }
        register.set((short) (register.get() - 0x1));
        if (register.get() == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
        registers.Flags.set(FlagKind.N);
    }

    private void INC(Register16 register) {
        // Set if carry from bit 3
        if ((register.get() & 0x0f) == 0x0f) {
            registers.Flags.set(FlagKind.H);
        }
        // TODO não entendi essa regra de "& 0xff"?
        register.set((short) ((register.get() + 0x1) & 0xff));
        if (register.get() == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
        registers.Flags.reset(FlagKind.N);
    }

    private void LDD16n() {
        // TODO migrar esse load para uma chamada de outra instrução
        mmu.set(registers.HL.get(), registers.A.get());
        DEC(registers.HL);
    }

    private void LDAn(Register8 register) {
        registers.A.set(register.get());
    }

    private void LDAn(Register16 register) {
        registers.A.set(mmu.get(register.get()));
    }

    private void LDnA(Register8 register) {
        register.set(registers.A.get());
    }

    private void LDnA(Register16 register) {
        mmu.set(register.get(), registers.A.get());
    }

    private void LDHnA() {
        mmu.set((short) (0xff00 + fetch()), registers.A.get());
    }

    private void LD8(Register8 register, byte data) {
        register.set(data);
    }

    private void LDcA() {
        mmu.set((short) (0xff00 + registers.C.get()), registers.A.get());
    }

    private void INC(Register8 register) {
        // Set if carry from bit 3
        if ((register.get() & 0x0f) == 0x0f) {
            registers.Flags.set(FlagKind.H);
        }
        // TODO não entendi essa regra de "& 0xff"?
        register.set((byte) ((register.get() + 0x1) & 0xff));
        if (register.get() == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
        registers.Flags.reset(FlagKind.N);
    }

    private void CALL() {
        short address = fetch16();
        DEC(registers.SP);
        mmu.set(registers.SP.get(), fetch());
        registers.PC.set(address);
    }

    private void PUSH(Register16 register) {
        byte high = (byte) (register.get() >> 8);
        byte low = (byte) register.get();

        DEC(registers.SP);
        mmu.set(registers.SP.get(), high);
        DEC(registers.SP);
        mmu.set(registers.SP.get(), low);
        byte l = mmu.get(registers.SP.get());
    }

    private void RLA() {
        registers.A.set((byte) 0x70);
        registers.Flags.resetAll();
        if ((registers.A.get() >> 7 & 1) == 0x1) {
            registers.Flags.set(FlagKind.C);
        }
        registers.A.set((byte) ((registers.A.get() << 1) + registers.Flags.get(FlagKind.C)));

        if (registers.A.get() == 0x0) {
            registers.Flags.set(FlagKind.Z);
        }
    }

    private void POP(Register16 register) {
        byte l = mmu.get(registers.SP.get());
        INC(registers.SP);
        byte h = mmu.get(registers.SP.get());
        INC(registers.SP);

        register.set(BinaryHelper.wordFromBytes(h, l));
    }
}
