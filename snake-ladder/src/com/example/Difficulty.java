package com.example;

public enum Difficulty {
    EASY,
    HARD;

    public static Difficulty from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Difficulty must be easy or hard.");
        }

        String normalized = value.trim().toLowerCase();
        if ("easy".equals(normalized)) {
            return EASY;
        }
        if ("hard".equals(normalized)) {
            return HARD;
        }
        throw new IllegalArgumentException("Difficulty must be easy or hard.");
    }
}
