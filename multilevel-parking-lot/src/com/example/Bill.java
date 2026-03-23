package com.example;

public class Bill {
    private final String ticketId;
    private final long durationMinutes;
    private final long billedHours;
    private final SlotType chargedSlotType;
    private final double amount;

    public Bill(String ticketId, long durationMinutes, long billedHours, SlotType chargedSlotType, double amount) {
        this.ticketId = ticketId;
        this.durationMinutes = durationMinutes;
        this.billedHours = billedHours;
        this.chargedSlotType = chargedSlotType;
        this.amount = amount;
    }

    public String getTicketId() {
        return ticketId;
    }

    public long getDurationMinutes() {
        return durationMinutes;
    }

    public long getBilledHours() {
        return billedHours;
    }

    public SlotType getChargedSlotType() {
        return chargedSlotType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "ticketId='" + ticketId + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", billedHours=" + billedHours +
                ", chargedSlotType=" + chargedSlotType +
                ", amount=" + amount +
                '}';
    }
}
