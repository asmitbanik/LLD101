package com.example;

public class VehicleDetails {
    private final String registrationNumber;
    private final VehicleType vehicleType;

    public VehicleDetails(String registrationNumber, VehicleType vehicleType) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number is required.");
        }
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type is required.");
        }
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
