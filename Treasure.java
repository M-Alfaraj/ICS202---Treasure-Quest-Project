/*
 * Treasure class:
 * Represents the treasure’s position in the grid.
 */
public class Treasure
{
    // The current grid cell of the treasure
    Location location;

    /**
     * Constructor for Treasure.
     *
     * @param row    the starting row index of the treasure
     * @param column the starting column index of the treasure
     */
    public Treasure(int row, int column)
    {
        // Initialize the location field with provided coordinates
        this.location = new Location(row, column);
    }
}