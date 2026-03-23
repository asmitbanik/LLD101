package com.example;

public class Player {
    private final String name;
    private int position;
    private boolean winner;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.winner = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isWinner() {
        return winner;
    }

    public void markWinner() {
        this.winner = true;
    }
}
