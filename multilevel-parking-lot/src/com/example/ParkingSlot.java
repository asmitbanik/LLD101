package com.example;

public class ParkingSlot {
    private final String slotNumber;
    private final int floorNumber;
    private final int positionIndex;
    private final SlotType slotType;
    private boolean occupied;

    public ParkingSlot(String slotNumber, int floorNumber, int positionIndex, SlotType slotType) {
        this.slotNumber = slotNumber;
        this.floorNumber = floorNumber;
        this.positionIndex = positionIndex;
        this.slotType = slotType;
        this.occupied = false;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void free() {
        occupied = false;
    }
}
