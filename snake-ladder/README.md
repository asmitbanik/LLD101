# Snake and Ladder

Console-based Snake and Ladder game.

## Inputs

The program asks for:
- `n`: board size, board has `n x n` cells
- `x`: number of players
- `difficulty_level`: `easy` or `hard`

## Rules Implemented

- Board cells go from `1` to `n^2`.
- Each player starts at `0` (outside board).
- A six-sided dice (1 to 6) is rolled randomly each turn.
- Turn-by-turn play across all active players.
- If move goes beyond last cell (`n^2`), player does not move.
- Landing on snake head moves player down to snake tail.
- Landing on ladder start moves player up to ladder end.
- There are `n` snakes and `n` ladders, placed randomly.
- Snake head is always greater than tail.
- Ladder end is always greater than start.
- Jump graph is generated without cycles.
- Game continues until fewer than 2 players are still competing to win.

## Difficulty

- `easy`: shorter snakes, longer ladders
- `hard`: longer snakes, shorter ladders

## Compile and Run

```powershell
Set-Location "d:\Asmit\LLD101\snake-ladder"
if (Test-Path out) { Remove-Item -Recurse -Force out }
New-Item -ItemType Directory -Path out | Out-Null
javac -d out src\com\example\*.java
java -cp out com.example.Main
```

## Sample Input

```text
6
3
easy
```
