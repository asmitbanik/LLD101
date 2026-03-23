package com.example;

public class BallPen implements Pen {
    private final String brand;
    private final double tipSizeMm;
    private final InkCartridge cartridge;
    private PenState state;

    public BallPen(String brand, double tipSizeMm, int inkCapacityMl) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand must not be blank.");
        }
        if (tipSizeMm <= 0) {
            throw new IllegalArgumentException("Tip size must be greater than zero.");
        }

        this.brand = brand;
        this.tipSizeMm = tipSizeMm;
        this.cartridge = new InkCartridge(inkCapacityMl);
        this.state = PenState.CLOSED;
    }

    @Override
    public void start() {
        state = PenState.OPEN;
    }

    @Override
    public String write(String text) {
        if (state != PenState.OPEN) {
            throw new IllegalStateException("Pen is closed. Call start() before write().");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text to write must not be blank.");
        }
        if (!cartridge.hasInk()) {
            throw new IllegalStateException("Out of ink. Call refill() before writing.");
        }

        // Simple ink consumption model: at least 1 ml, otherwise based on text length.
        int inkToConsume = Math.max(1, text.length() / 10);
        if (inkToConsume > cartridge.getRemainingMl()) {
            inkToConsume = cartridge.getRemainingMl();
        }

        cartridge.consume(inkToConsume);
        return brand + " (" + tipSizeMm + "mm): " + text;
    }

    @Override
    public void close() {
        state = PenState.CLOSED;
    }

    @Override
    public void refill() {
        cartridge.refill();
    }

    public int inkLeftMl() {
        return cartridge.getRemainingMl();
    }
}
