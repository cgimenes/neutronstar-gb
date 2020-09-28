package com.gimenes.neutronstargb.hardware.cpu;

class Stack {
    private final java.util.Stack<Byte> stack;

    public Stack() {
        stack = new java.util.Stack<>();
    }

    public void push(byte data) {
        stack.push(data);
    }

    public byte pop() {
        return stack.pop();
    }
}
