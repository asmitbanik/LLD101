package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SnakeLadderGame {
    private final Board board;
    private final Dice dice;
    private final List<Player> players;

    public SnakeLadderGame(Board board, Dice dice, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
    }

    public void play() {
        printBoardSummary();

        List<Player> winners = new ArrayList<>();
        int turn = 1;

        while (activePlayers() >= 2) {
            for (Player player : players) {
                if (player.isWinner()) {
                    continue;
                }

                takeTurn(player, turn);

                if (player.getPosition() == board.getFinalCell()) {
                    player.markWinner();
                    winners.add(player);
                    System.out.println(player.getName() + " has reached cell " + board.getFinalCell() + " and won rank " + winners.size() + ".");
                }

                if (activePlayers() < 2) {
                    break;
                }
            }
            turn++;
        }

        System.out.println();
        System.out.println("Game over. Final standings:");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println((i + 1) + ". " + winners.get(i).getName());
        }

        for (Player player : players) {
            if (!player.isWinner()) {
                System.out.println("Still in game when stopped: " + player.getName() + " at cell " + player.getPosition());
            }
        }
    }

    private void takeTurn(Player player, int turn) {
        int roll = dice.roll();
        int current = player.getPosition();
        int target = current + roll;

        System.out.println();
        System.out.println("Turn " + turn + " - " + player.getName() + " rolls " + roll + ".");

        if (target > board.getFinalCell()) {
            System.out.println("Move exceeds last cell " + board.getFinalCell() + ". " + player.getName() + " stays at " + current + ".");
            return;
        }

        player.setPosition(target);
        System.out.println(player.getName() + " moves from " + current + " to " + target + ".");

        if (board.hasSnakeAt(target)) {
            int newCell = board.moveByJump(target);
            player.setPosition(newCell);
            System.out.println("Snake! " + player.getName() + " goes down to " + newCell + ".");
        } else if (board.hasLadderAt(target)) {
            int newCell = board.moveByJump(target);
            player.setPosition(newCell);
            System.out.println("Ladder! " + player.getName() + " climbs up to " + newCell + ".");
        }
    }

    private int activePlayers() {
        int count = 0;
        for (Player player : players) {
            if (!player.isWinner()) {
                count++;
            }
        }
        return count;
    }

    private void printBoardSummary() {
        System.out.println("Board: " + board.getSize() + "x" + board.getSize() + " (" + board.getFinalCell() + " cells)");
        printMap("Snakes", board.getSnakeMap());
        printMap("Ladders", board.getLadderMap());
    }

    private void printMap(String label, Map<Integer, Integer> map) {
        System.out.println(label + " (" + map.size() + "):");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
