package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter board size n (board is n x n): ");
            int n = Integer.parseInt(scanner.nextLine().trim());
            if (n < 3) {
                throw new IllegalArgumentException("n must be at least 3.");
            }

            System.out.print("Enter number of players x: ");
            int x = Integer.parseInt(scanner.nextLine().trim());
            if (x < 2) {
                throw new IllegalArgumentException("At least 2 players are required.");
            }

            System.out.print("Enter difficulty level (easy/hard): ");
            Difficulty difficulty = Difficulty.from(scanner.nextLine());

            Board board = new BoardFactory().create(n, difficulty);
            Dice dice = new RandomDice();
            List<Player> players = new ArrayList<>();

            for (int i = 1; i <= x; i++) {
                players.add(new Player("Player-" + i));
            }

            SnakeLadderGame game = new SnakeLadderGame(board, dice, players);
            game.play();
        } catch (NumberFormatException ex) {
            System.out.println("Invalid numeric input. Please enter valid integers for n and x.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}
