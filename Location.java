/*
 * Location class:
 * Encapsulates a grid coordinate (row and column) in the dungeon.
 */
public class Location
{
    private int row;     // the row index of this location
    private int column;  // the column index of this location

    /**
     * Constructor for Location.
     *
     * @param row    the row index to store
     * @param column the column index to store
     */
    public Location(int row, int column)
    {
        this.row = row;       // store the provided row index
        this.column = column; // store the provided column index
    }

    /**
     * @return the stored row index of this location
     */
    public int getRow()
    {
        return row;
    }

    /**
     * @return the stored column index of this location
     */
    public int getColumn()
    {
        return column;
    }
}
