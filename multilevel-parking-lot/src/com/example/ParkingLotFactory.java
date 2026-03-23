package com.example;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ParkingLotFactory {
    private ParkingLotFactory() {
    }

    public static ParkingLot createDemoLot() {
        List<EntryGate> gates = List.of(
                new EntryGate("G1", 1),
                new EntryGate("G2", 2),
                new EntryGate("G3", 3));

        List<ParkingSlot> slots = new ArrayList<>();

        // Floor 1
        slots.add(new ParkingSlot("F1-S1", 1, 1, SlotType.SMALL));
        slots.add(new ParkingSlot("F1-S2", 1, 2, SlotType.SMALL));
        slots.add(new ParkingSlot("F1-M1", 1, 3, SlotType.MEDIUM));
        slots.add(new ParkingSlot("F1-L1", 1, 4, SlotType.LARGE));

        // Floor 2
        slots.add(new ParkingSlot("F2-S1", 2, 1, SlotType.SMALL));
        slots.add(new ParkingSlot("F2-M1", 2, 2, SlotType.MEDIUM));
        slots.add(new ParkingSlot("F2-M2", 2, 3, SlotType.MEDIUM));
        slots.add(new ParkingSlot("F2-L1", 2, 4, SlotType.LARGE));

        // Floor 3
        slots.add(new ParkingSlot("F3-S1", 3, 1, SlotType.SMALL));
        slots.add(new ParkingSlot("F3-M1", 3, 2, SlotType.MEDIUM));
        slots.add(new ParkingSlot("F3-L1", 3, 3, SlotType.LARGE));
        slots.add(new ParkingSlot("F3-L2", 3, 4, SlotType.LARGE));

        Map<SlotType, Double> rates = new EnumMap<>(SlotType.class);
        rates.put(SlotType.SMALL, 20.0);
        rates.put(SlotType.MEDIUM, 40.0);
        rates.put(SlotType.LARGE, 80.0);

        return new ParkingLot(gates, slots, rates);
    }
}
