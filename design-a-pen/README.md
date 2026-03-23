# Design a Pen

This module contains:
- A simple class design for a `Pen`
- A Java implementation with the required behaviors: `start`, `write`, `close`, and `refill`

## Class Diagram

```mermaid
classDiagram
    class InkCartridge {
        -int capacityMl
        -int remainingMl
        +InkCartridge(int capacityMl)
        +boolean hasInk()
        +void consume(int amountMl)
        +void refill()
        +int getRemainingMl()
        +int getCapacityMl()
    }

    class Pen {
        <<interface>>
        +void start()
        +String write(String text)
        +void close()
        +void refill()
    }

    class BallPen {
        -String brand
        -double tipSizeMm
        -InkCartridge cartridge
        -PenState state
        +BallPen(String brand, double tipSizeMm, int inkCapacityMl)
        +void start()
        +String write(String text)
        +void close()
        +void refill()
        +int inkLeftMl()
    }

    class PenState {
        <<enumeration>>
        CLOSED
        OPEN
    }

    Pen <|.. BallPen
    BallPen *-- InkCartridge
    BallPen --> PenState
```

## Notes

- `start()` opens the pen for writing.
- `write(text)` writes only when the pen is started and ink is available.
- `close()` closes the pen.
- `refill()` restores ink to full cartridge capacity.
