package com.example;

import java.util.Random;

public class RandomDice implements Dice {
    private final Random random;

    public RandomDice() {
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
