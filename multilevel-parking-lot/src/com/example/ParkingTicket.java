package com.example;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String ticketId;
    private final VehicleDetails vehicleDetails;
    private final String allocatedSlotNumber;
    private final SlotType allocatedSlotType;
    private final String entryGateId;
    private final LocalDateTime entryTime;

    public ParkingTicket(
            String ticketId,
            VehicleDetails vehicleDetails,
            String allocatedSlotNumber,
            SlotType allocatedSlotType,
            String entryGateId,
            LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicleDetails = vehicleDetails;
        this.allocatedSlotNumber = allocatedSlotNumber;
        this.allocatedSlotType = allocatedSlotType;
        this.entryGateId = entryGateId;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public String getAllocatedSlotNumber() {
        return allocatedSlotNumber;
    }

    public SlotType getAllocatedSlotType() {
        return allocatedSlotType;
    }

    public String getEntryGateId() {
        return entryGateId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
