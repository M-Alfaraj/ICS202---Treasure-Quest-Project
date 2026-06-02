# ICS202

Treasure Quest is a Java-based grid pathfinding project developed for the Data Structures course. The goal is to find the shortest safe path from the starting point S to the treasure E inside a dungeon grid while avoiding walls and monster-monitored areas. The project uses Breadth-First Search (BFS) to guarantee the shortest path in an unweighted grid and applies multiple data structures to efficiently model the environment and constraints.
## Team
TURKI SALEM HUSSAIN AZAYBI - 202223860
MOHAMMED SALAH ALFARAJ - 202323090

##Problem Overview
The dungeon is represented as a grid where:

S = Start position

E = Treasure

# = Wall

. = Open path

Monsters monitor surrounding cells based on their movement range, making those cells unsafe. The player must reach the treasure using the shortest possible safe path or output IMPOSSIBLE if no path exists.

## Features
- Grid-based dungeon modeling
- Monster danger-zone processing
- Shortest path computation using BFS
- Java implementation using standard data structures

## Data Structures Used
- 2D char array → dungeon grid representation
- 2D int array → distance tracking
- 2D boolean array → monitored (unsafe) cells
- Queue (ArrayDeque) → BFS traversal

## Algorithm
- Breadth-First Search (BFS)
- Explores 4-directional movement (up, down, left, right)
- Guarantees shortest path in an unweighted grid

## Input
- Grid size n × m
- Dungeon layout
- Number of monsters k
- Monster positions and ranges

## Output
Shortest path length from S to E

OR

IMPOSSIBLE
 
## How to Run
1. Compile all Java files
2. Run the main class
3. Provide input through console

## Tech Stack
- Java
- Data Structures
