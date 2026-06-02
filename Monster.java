public class Monster
{
    Location location;      // the current cell
    int stepsTaken;         // number of steps moved from the start
    int monsterRange;       // maximum vision range in steps

    /**
     * @param row          starting/current row
     * @param column       starting/current column
     * @param stepsTaken   how many steps from the origin (0 at start)
     * @param monsterRange max steps the monster can “see”
     */
    public Monster(int row, int column, int stepsTaken, int monsterRange) {
        this.location = new Location(row, column);
        this.stepsTaken = stepsTaken;
        this.monsterRange = monsterRange;
    }
}