package com.gimenes.neutronstargb.hardware.cpu.flags;

public enum FlagKind {
    /*
     * Zero Flag
     * This bit is set when the result of a math operation
     * is zero or two values match when using the CP
     * instruction.
     */
    Z,

    /*
     * Subtract Flag
     * This bit is set if a subtraction was performed in the
     * last math instruction.
     */
    N,

    /*
     * Half Carry Flag
     * This bit is set if a carry occurred from the lower
     * nibble in the last math operation.
     */
    H,

    /*
     * Carry Flag
     * This bit is set if a carry occurred from the las
     * math operation or if register A is the smaller value
     * when executing the CP instruction.
     */
    C
}
