package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final int finalCell;
    private final Map<Integer, Integer> snakeMap;
    private final Map<Integer, Integer> ladderMap;

    public Board(int size, Map<Integer, Integer> snakeMap, Map<Integer, Integer> ladderMap) {
        this.size = size;
        this.finalCell = size * size;
        this.snakeMap = new HashMap<>(snakeMap);
        this.ladderMap = new HashMap<>(ladderMap);
    }

    public int getSize() {
        return size;
    }

    public int getFinalCell() {
        return finalCell;
    }

    public Map<Integer, Integer> getSnakeMap() {
        return Collections.unmodifiableMap(snakeMap);
    }

    public Map<Integer, Integer> getLadderMap() {
        return Collections.unmodifiableMap(ladderMap);
    }

    public boolean hasSnakeAt(int cell) {
        return snakeMap.containsKey(cell);
    }

    public boolean hasLadderAt(int cell) {
        return ladderMap.containsKey(cell);
    }

    public int moveByJump(int cell) {
        if (hasSnakeAt(cell)) {
            return snakeMap.get(cell);
        }
        if (hasLadderAt(cell)) {
            return ladderMap.get(cell);
        }
        return cell;
    }
}
