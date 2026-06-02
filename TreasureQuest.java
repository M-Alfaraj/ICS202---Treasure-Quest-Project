import java.util.*;
public class TreasureQuest
{
    /**
     * Performs a breadth-first search (BFS) outward from the monster's starting position
     * to mark every reachable cell within the monster's vision range.
     *
     * @param monsterRowPosition          the row index where the monster starts
     * @param monsterColumnPosition       the column index where the monster starts
     * @param monsterRange                maximum number of steps (cells) the monster can "see"
     * @param map                         2D char array of the dungeon ('#' for walls, others for open)
     * @param monitoredLocationsByMonster 2D boolean array to mark monitored cells
     */
    public static void monsterMonitoring(int monsterRowPosition, int monsterColumnPosition, int monsterRange, char[][] map, boolean[][] monitoredLocationsByMonster)
    {
        // Determine the number of rows (height) of the dungeon grid
        int n = map.length;

        // Determine the number of columns (width) of the dungeon grid
        int m = map[0].length;

        // Create a grid to track which cells have been visited by our BFS
        // This prevents revisiting and infinite loops
        boolean[][] visited = new boolean[n][m];

        // Use a queue to perform BFS; each queue entry holds a Monster state
        Queue<Monster> q = new ArrayDeque<>();

        // Enqueue the monster's starting cell with stepsTaken = 0
        q.add(new Monster(
                monsterRowPosition,    // starting row
                monsterColumnPosition, // starting column
                0,                     // steps traveled so far
                monsterRange           // vision range limit
        ));

        // Mark the starting cell as visited
        visited[monsterRowPosition][monsterColumnPosition] = true;

        // Mark the starting cell as monitored
        monitoredLocationsByMonster[monsterRowPosition][monsterColumnPosition] = true;

        // Continue until there are no more cells to explore
        while (!q.isEmpty())
        {
            // Remove the next state from the queue
            Monster current = q.poll();

            // If we've reached the vision limit, skip expanding neighbors
            if (current.stepsTaken == current.monsterRange)
            {
                continue;
            }

            // Directions: down, up, right, left
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            for (int[] dir : directions)
            {
                // Calculate neighbor's row and column
                int newRow = current.location.getRow() + dir[0];

                int newCol = current.location.getColumn() + dir[1];

                // Check bounds: row in [0, n), col in [0, m)
                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m)
                {
                    continue; // skip out-of-bounds cells
                }

                // Skip if already visited
                if (visited[newRow][newCol])
                {
                    continue;
                }

                // Skip walls
                if (map[newRow][newCol] == '#')
                {
                    continue;
                }

                // Mark this cell visited so we don't revisit
                visited[newRow][newCol] = true;

                // Mark this cell as within the monster's vision
                monitoredLocationsByMonster[newRow][newCol] = true;

                // Enqueue the neighbor with stepsTaken + 1
                q.add(new Monster(newRow, newCol, current.stepsTaken + 1, current.monsterRange));
            }
        }
    }

    public static void main(String[] args) {
        //Scanner to take the dungeon size, number of monsters, dungeon structure, and monster locations and range
        Scanner scnr = new Scanner(System.in);

        //n is for the rows and m is for the columns of the structure. k is the number of monsters in the dungeon
        try {
            int n = scnr.nextInt();
            int m = scnr.nextInt();
            int k = scnr.nextInt();
            scnr.nextLine();

            //Check the constraints of the dungeon
            if (n < 5 || m < 5 || n > 1000 || m > 1000 || k < 0 || k > 10000) {
                System.out.println("Invalid constraint values");
                System.out.println("rows and columns need to be >= 5 and <= 1000");
                System.out.println("Minimum number of monster is 0 and maximum is 10000");
            }

            //variables to store the structure of the dungeon, and player and treasure locations.
            char[][] dungeon = new char[n][m];
            Player player = null;
            Treasure treasure = null;
            //the stored variables serve as a way to prevent multiple players and treasures
            boolean playerStored = false;
            boolean treasureStored = false;

            for (int i = 0; i < n; i++) {
                // Read the next line of input from the user (representing a row of the dungeon map)
                String line = scnr.nextLine();

                // Check that the line contains only the allowed characters:
                //   '#' (wall), '.' (floor), 'S' or 's' (player), 'E' or 'e' (treasure)
                // If any other character is present, reject the input and terminate this method
                if (!line.matches("[#.SEes]+"))
                {
                    // Print an error message to standard error explaining the valid character set
                    System.err.println("Invalid input: only '#', '.', 'S', 'E', 'e', 's' are allowed");

                    // Stop processing (e.g., exit the current method or program)
                    return;
                }


                //loop to check the line's elements
                for (int j = 0; j < m; j++) {
                    //holds each element one by one and adds them to the 2D array
                    char element = line.charAt(j);
                    dungeon[i][j] = element;

                    //Make sure that the dungeon's walls are '#' not another thing else
                    if ((i == 0 || i == n - 1) && element != '#') {
                        System.out.println("IMPOSSIBLE");
                        return;
                    } else if ((j == 0 || j == m - 1) && element != '#') {
                        System.out.println("IMPOSSIBLE");
                        return;
                    }

                    //check if S or E is in element there and stores they're location
                    //if S and E are stored and another S or E is found, it will terminate the program
                    if ((element == 'S' || element == 's') && playerStored) {
                        System.out.println("IMPOSSIBLE");
                        return;
                    }
                    if ((element == 'E' || element == 'e') && treasureStored) {
                        System.out.println("IMPOSSIBLE");
                        return;
                    }
                    if (element == 'S' || element == 's') {
                        player = new Player(i, j);
                        playerStored = true;
                    }
                    if (element == 'E' || element == 'e') {
                        treasure = new Treasure(i, j);
                        treasureStored = true;
                    }
                }
            }

            //if no S or E value is found, terminate the program.
            if (player == null || treasure == null) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            //check the monster location and where its range covers
            boolean[][] monitoredLocations = new boolean[n][m];
            for (int i = 0; i < k; i++) {
                //User inputs the row, column, and range of the monster.
                int monsterRow = scnr.nextInt() - 1;
                int monsterColumn = scnr.nextInt() - 1;
                int monsterRange = scnr.nextInt();

                //check the location of the monster if it is inside and makes sure that it is not outside the grid and not on a wall
                if (monsterRow <= 1 || monsterRow >= n - 1 || monsterColumn <= 1 || monsterColumn >= m - 1 || monsterRange < 0 || monsterRange > n * m || dungeon[monsterRow][monsterColumn] == '#') {
                    System.out.println("Invalid monster constraints");
                    System.out.println("Monster(s) need to be inside the grid and on a wall");
                    System.out.println("Monster range needs to be <= the multiplication of the column and row of the dungeon");
                    return;
                }

                //calls the monitor method
                monsterMonitoring(monsterRow, monsterColumn, monsterRange, dungeon, monitoredLocations);
            }

            //if the monster is on or monitoring the treasure or player, then it would be impossible to complete the path
            if (monitoredLocations[player.location.getRow()][player.location.getColumn()] || monitoredLocations[treasure.location.getRow()][treasure.location.getColumn()]) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            // variable to store all distances
            int[][] distances = new int[n][m];


            //BFS algorithm to initialize all values to -1 to represent unvisited
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances[i].length; j++) {
                    distances[i][j] = -1;
                }
            }

            //Queue used for BFS to check to visit next
            Queue<Player> queue = new ArrayDeque<>();
            queue.add(player);

            //Set the starting location of the player in distances to 0
            distances[player.location.getRow()][player.location.getColumn()] = 0;

            //while loops until the queue is empty
            while (!queue.isEmpty()) {
                //dequeues the first element and stores it to position
                Player position = queue.poll();

                //if the treasure is found, then exit the loop.
                if (position.location.getRow() == treasure.location.getRow() && position.location.getColumn() == treasure.location.getColumn()) {
                    break;
                }

                //direction that the player can move. Up, Down, Right, Left.
                int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int i = 0; i < directions.length; i++) {
                    //Checks the next position that the player can move to for the path
                    int dr = directions[i][0];
                    int dc = directions[i][1];
                    int nextRow = position.location.getRow() + dr;
                    int nextColumn = position.location.getColumn() + dc;

                    //checks if the next position is valid to move to.
                    if (nextRow >= 0 && nextRow < n && nextColumn >= 0 && nextColumn < m && dungeon[nextRow][nextColumn] != '#' && !monitoredLocations[nextRow][nextColumn] && distances[nextRow][nextColumn] == -1) {
                        distances[nextRow][nextColumn] = distances[position.location.getRow()][position.location.getColumn()] + 1;
                        queue.add(new Player(nextRow, nextColumn));
                    }
                }
            }

            //gets the shortest distance to the treasure's location
            int shortestDistance = distances[treasure.location.getRow()][treasure.location.getColumn()];

            if (shortestDistance >= 0) {
                System.out.println(shortestDistance);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
        catch (Exception e){
            System.out.println("Enter only numbers for the chamber layout and monster constraints");
        }
    }
}