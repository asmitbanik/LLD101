package com.example;

public class Jump {
    private final int start;
    private final int end;
    private final JumpType type;

    public Jump(int start, int end, JumpType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public JumpType getType() {
        return type;
    }
}
