package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BoardFactory {
    private final Random random;

    public BoardFactory() {
        this.random = new Random();
    }

    public Board create(int size, Difficulty difficulty) {
        int finalCell = size * size;
        int count = size;

        if (size < 3) {
            throw new IllegalArgumentException("Board size n must be at least 3.");
        }

        Map<Integer, Integer> snakes = new HashMap<>();
        Map<Integer, Integer> ladders = new HashMap<>();
        Map<Integer, Integer> allJumps = new HashMap<>();
        Set<Integer> usedCells = new HashSet<>();

        placeSnakes(count, finalCell, difficulty, snakes, allJumps, usedCells);
        placeLadders(count, finalCell, difficulty, ladders, allJumps, usedCells);

        return new Board(size, snakes, ladders);
    }

    private void placeSnakes(
            int count,
            int finalCell,
            Difficulty difficulty,
            Map<Integer, Integer> snakes,
            Map<Integer, Integer> allJumps,
            Set<Integer> usedCells) {

        int placed = 0;
        int attempts = 0;
        int maxAttempts = 20000;

        while (placed < count && attempts < maxAttempts) {
            attempts++;

            int head = randomCell(2, finalCell - 1);
            int minDrop = difficulty == Difficulty.EASY ? 1 : 3;
            int maxDrop = difficulty == Difficulty.EASY ? Math.max(2, finalCell / 6) : Math.max(4, finalCell / 3);
            int tail = head - randomCell(minDrop, maxDrop);

            if (tail < 1) {
                continue;
            }
            if (head <= tail) {
                continue;
            }
            if (!isUnused(head, tail, usedCells)) {
                continue;
            }
            if (tail == finalCell) {
                continue;
            }

            allJumps.put(head, tail);
            if (createsCycle(head, allJumps)) {
                allJumps.remove(head);
                continue;
            }

            snakes.put(head, tail);
            usedCells.add(head);
            usedCells.add(tail);
            placed++;
        }

        if (placed < count) {
            throw new IllegalStateException("Could not place all snakes. Try a larger board size.");
        }
    }

    private void placeLadders(
            int count,
            int finalCell,
            Difficulty difficulty,
            Map<Integer, Integer> ladders,
            Map<Integer, Integer> allJumps,
            Set<Integer> usedCells) {

        int placed = 0;
        int attempts = 0;
        int maxAttempts = 20000;

        while (placed < count && attempts < maxAttempts) {
            attempts++;

            int start = randomCell(2, finalCell - 2);
            int minRise = difficulty == Difficulty.EASY ? 3 : 1;
            int maxRise = difficulty == Difficulty.EASY ? Math.max(4, finalCell / 3) : Math.max(2, finalCell / 6);
            int end = start + randomCell(minRise, maxRise);

            if (end > finalCell - 1) {
                continue;
            }
            if (end <= start) {
                continue;
            }
            if (!isUnused(start, end, usedCells)) {
                continue;
            }

            allJumps.put(start, end);
            if (createsCycle(start, allJumps)) {
                allJumps.remove(start);
                continue;
            }

            ladders.put(start, end);
            usedCells.add(start);
            usedCells.add(end);
            placed++;
        }

        if (placed < count) {
            throw new IllegalStateException("Could not place all ladders. Try a larger board size.");
        }
    }

    private boolean isUnused(int source, int destination, Set<Integer> usedCells) {
        return !usedCells.contains(source) && !usedCells.contains(destination);
    }

    private int randomCell(int minInclusive, int maxInclusive) {
        if (minInclusive > maxInclusive) {
            return minInclusive;
        }
        return random.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
    }

    private boolean createsCycle(int start, Map<Integer, Integer> jumpMap) {
        Set<Integer> seen = new HashSet<>();
        int current = start;

        while (jumpMap.containsKey(current)) {
            if (!seen.add(current)) {
                return true;
            }
            current = jumpMap.get(current);
        }
        return false;
    }
}
