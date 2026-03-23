package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final Map<String, EntryGate> gatesById;
    private final List<ParkingSlot> slots;
    private final Map<String, ParkingSlot> slotByNumber;
    private final Map<SlotType, Double> hourlyRateBySlotType;

    public ParkingLot(List<EntryGate> gates, List<ParkingSlot> slots, Map<SlotType, Double> hourlyRateBySlotType) {
        this.gatesById = new HashMap<>();
        for (EntryGate gate : gates) {
            this.gatesById.put(gate.getGateId(), gate);
        }

        this.slots = slots;
        this.slotByNumber = new HashMap<>();
        for (ParkingSlot slot : slots) {
            this.slotByNumber.put(slot.getSlotNumber(), slot);
        }

        this.hourlyRateBySlotType = new HashMap<>(hourlyRateBySlotType);
    }

    public EntryGate getGate(String gateId) {
        return gatesById.get(gateId);
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public ParkingSlot getSlot(String slotNumber) {
        return slotByNumber.get(slotNumber);
    }

    public double getHourlyRate(SlotType slotType) {
        Double rate = hourlyRateBySlotType.get(slotType);
        if (rate == null) {
            throw new IllegalStateException("No hourly rate configured for slot type " + slotType);
        }
        return rate;
    }
}
