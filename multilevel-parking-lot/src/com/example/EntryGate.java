package com.example;

public class EntryGate {
    private final String gateId;
    private final int floorNumber;

    public EntryGate(String gateId, int floorNumber) {
        if (gateId == null || gateId.isBlank()) {
            throw new IllegalArgumentException("Gate ID is required.");
        }
        this.gateId = gateId;
        this.floorNumber = floorNumber;
    }

    public String getGateId() {
        return gateId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
