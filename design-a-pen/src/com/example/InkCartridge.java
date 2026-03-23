package com.example;

public class InkCartridge {
    private final int capacityMl;
    private int remainingMl;

    public InkCartridge(int capacityMl) {
        if (capacityMl <= 0) {
            throw new IllegalArgumentException("Ink capacity must be greater than zero.");
        }
        this.capacityMl = capacityMl;
        this.remainingMl = capacityMl;
    }

    public boolean hasInk() {
        return remainingMl > 0;
    }

    public void consume(int amountMl) {
        if (amountMl <= 0) {
            throw new IllegalArgumentException("Consumed ink must be positive.");
        }
        if (amountMl > remainingMl) {
            throw new IllegalStateException("Not enough ink left in cartridge.");
        }
        remainingMl -= amountMl;
    }

    public void refill() {
        remainingMl = capacityMl;
    }

    public int getRemainingMl() {
        return remainingMl;
    }

    public int getCapacityMl() {
        return capacityMl;
    }
}
