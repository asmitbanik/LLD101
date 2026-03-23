package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotService {
    private final ParkingLot parkingLot;
    private final Map<String, ParkingTicket> activeTicketsById;
    private final Map<String, String> activeTicketByVehicleReg;
    private long ticketSequence;

    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.activeTicketsById = new HashMap<>();
        this.activeTicketByVehicleReg = new HashMap<>();
        this.ticketSequence = 1;
    }

    public ParkingTicket park(
            VehicleDetails vehicleDetails,
            LocalDateTime entryTime,
            SlotType requestedSlotType,
            String entryGateID) {

        validateParkRequest(vehicleDetails, entryTime, requestedSlotType, entryGateID);

        EntryGate gate = parkingLot.getGate(entryGateID);
        List<ParkingSlot> candidates = new ArrayList<>();

        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (slot.isOccupied()) {
                continue;
            }
            if (!slot.getSlotType().isAtLeast(requestedSlotType)) {
                continue;
            }
            if (!isCompatible(vehicleDetails.getVehicleType(), slot.getSlotType())) {
                continue;
            }
            candidates.add(slot);
        }

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No compatible slot available for vehicle " + vehicleDetails.getRegistrationNumber());
        }

        candidates.sort(Comparator
                .comparingInt((ParkingSlot slot) -> distance(slot, gate))
                .thenComparingInt(ParkingSlot::getFloorNumber)
                .thenComparingInt(ParkingSlot::getPositionIndex));

        ParkingSlot chosen = candidates.get(0);
        chosen.occupy();

        String ticketId = "T-" + ticketSequence++;
        ParkingTicket ticket = new ParkingTicket(
                ticketId,
                vehicleDetails,
                chosen.getSlotNumber(),
                chosen.getSlotType(),
                gate.getGateId(),
                entryTime);

        activeTicketsById.put(ticketId, ticket);
        activeTicketByVehicleReg.put(vehicleDetails.getRegistrationNumber(), ticketId);
        return ticket;
    }

    public Map<SlotType, Integer> status() {
        Map<SlotType, Integer> freeSlots = new EnumMap<>(SlotType.class);
        for (SlotType slotType : SlotType.values()) {
            freeSlots.put(slotType, 0);
        }

        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (!slot.isOccupied()) {
                freeSlots.put(slot.getSlotType(), freeSlots.get(slot.getSlotType()) + 1);
            }
        }

        return freeSlots;
    }

    public double exit(ParkingTicket parkingTicket, LocalDateTime exitTime) {
        Bill bill = generateBill(parkingTicket, exitTime);
        return bill.getAmount();
    }

    public Bill generateBill(ParkingTicket parkingTicket, LocalDateTime exitTime) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException("Parking ticket is required.");
        }
        if (exitTime == null) {
            throw new IllegalArgumentException("Exit time is required.");
        }

        ParkingTicket active = activeTicketsById.get(parkingTicket.getTicketId());
        if (active == null) {
            throw new IllegalArgumentException("Ticket is invalid or already used for exit: " + parkingTicket.getTicketId());
        }
        if (exitTime.isBefore(active.getEntryTime())) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }

        long durationMinutes = Duration.between(active.getEntryTime(), exitTime).toMinutes();
        long billedHours = Math.max(1L, (long) Math.ceil(durationMinutes / 60.0));
        SlotType slotType = active.getAllocatedSlotType();
        double amount = billedHours * parkingLot.getHourlyRate(slotType);

        ParkingSlot slot = parkingLot.getSlot(active.getAllocatedSlotNumber());
        if (slot != null) {
            slot.free();
        }

        activeTicketsById.remove(active.getTicketId());
        activeTicketByVehicleReg.remove(active.getVehicleDetails().getRegistrationNumber());

        return new Bill(active.getTicketId(), durationMinutes, billedHours, slotType, amount);
    }

    private void validateParkRequest(
            VehicleDetails vehicleDetails,
            LocalDateTime entryTime,
            SlotType requestedSlotType,
            String entryGateID) {

        if (vehicleDetails == null) {
            throw new IllegalArgumentException("Vehicle details are required.");
        }
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time is required.");
        }
        if (requestedSlotType == null) {
            throw new IllegalArgumentException("Requested slot type is required.");
        }
        if (entryGateID == null || entryGateID.isBlank()) {
            throw new IllegalArgumentException("Entry gate ID is required.");
        }
        if (parkingLot.getGate(entryGateID) == null) {
            throw new IllegalArgumentException("Unknown entry gate: " + entryGateID);
        }
        if (!isCompatible(vehicleDetails.getVehicleType(), requestedSlotType)) {
            throw new IllegalArgumentException("Requested slot type " + requestedSlotType + " is not compatible with " + vehicleDetails.getVehicleType());
        }
        if (activeTicketByVehicleReg.containsKey(vehicleDetails.getRegistrationNumber())) {
            throw new IllegalStateException("Vehicle is already parked: " + vehicleDetails.getRegistrationNumber());
        }
    }

    private boolean isCompatible(VehicleType vehicleType, SlotType slotType) {
        if (vehicleType == VehicleType.TWO_WHEELER) {
            return true;
        }
        if (vehicleType == VehicleType.CAR) {
            return slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
        }
        return slotType == SlotType.LARGE;
    }

    private int distance(ParkingSlot slot, EntryGate gate) {
        int floorPenalty = Math.abs(slot.getFloorNumber() - gate.getFloorNumber()) * 1000;
        int lateralDistance = Math.abs(slot.getPositionIndex() - 1);
        return floorPenalty + lateralDistance;
    }
}
