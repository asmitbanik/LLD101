package com.example;

import java.time.LocalDateTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLotFactory.createDemoLot();
        ParkingLotService service = new ParkingLotService(lot);

        LocalDateTime t0 = LocalDateTime.of(2026, 3, 23, 10, 0);

        ParkingTicket bikeTicket = service.park(
                new VehicleDetails("BIKE-1001", VehicleType.TWO_WHEELER),
                t0,
                SlotType.SMALL,
                "G1");

        ParkingTicket carTicket = service.park(
                new VehicleDetails("CAR-9001", VehicleType.CAR),
                t0.plusMinutes(10),
                SlotType.MEDIUM,
                "G2");

        ParkingTicket busTicket = service.park(
                new VehicleDetails("BUS-77", VehicleType.BUS),
                t0.plusMinutes(20),
                SlotType.LARGE,
                "G3");

        System.out.println("Allocated bike slot: " + bikeTicket.getAllocatedSlotNumber() + " (" + bikeTicket.getAllocatedSlotType() + ")");
        System.out.println("Allocated car slot: " + carTicket.getAllocatedSlotNumber() + " (" + carTicket.getAllocatedSlotType() + ")");
        System.out.println("Allocated bus slot: " + busTicket.getAllocatedSlotNumber() + " (" + busTicket.getAllocatedSlotType() + ")");

        Map<SlotType, Integer> freeBeforeExit = service.status();
        System.out.println("Status before exits: " + freeBeforeExit);

        Bill bikeBill = service.generateBill(bikeTicket, t0.plusHours(2).plusMinutes(5));
        Bill carBill = service.generateBill(carTicket, t0.plusHours(1).plusMinutes(30));
        double busAmount = service.exit(busTicket, t0.plusHours(3));

        System.out.println("Bike bill: " + bikeBill);
        System.out.println("Car bill: " + carBill);
        System.out.println("Bus bill amount: " + busAmount);

        Map<SlotType, Integer> freeAfterExit = service.status();
        System.out.println("Status after exits: " + freeAfterExit);
    }
}
