package com.example;

public enum SlotType {
    SMALL,
    MEDIUM,
    LARGE;

    public boolean isAtLeast(SlotType other) {
        return this.ordinal() >= other.ordinal();
    }
}
