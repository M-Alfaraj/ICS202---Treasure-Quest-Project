/*
 * Player class:
 * Represents the player’s position in the grid.
 */
public class Player
{
    // The current grid cell of the player
    Location location;

    /**
     * Constructor for Player.
     *
     * @param row    the starting row index of the player
     * @param column the starting column index of the player
     */
    public Player(int row, int column)
    {
        // Initialize the location field with provided coordinates
        this.location = new Location(row, column);
    }
}