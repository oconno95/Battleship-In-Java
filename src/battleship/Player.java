package battleship;

public abstract class Player {

    private Grid grid;
    protected String name = "Default Player";

    public Player() {
        grid = new Grid();
    }

    public Grid getGrid() {
        return grid;
    }

    public int fireAt(Player p) {
        int[] fireLocation = this.getLocationToFireAt(p.getGrid());
        return p.getGrid().guessPositon(fireLocation[0], fireLocation[1]);
    }

    public boolean lostAllShips() {
        return this.grid.isAllShipsDestroyed();
    }

    public void printMissMessage() {
        System.out.println(this.name + " has missed.");
    }

    public void printHitMessage() {
        System.out.println(this.name + " has hit a ship.");
    }

    public void printSunkMessage() {
        System.out.println(this.name + " has sunk a ship.");
    }

    public void printWinMessage() {
        System.out.println(this.name + " has won the game.");
    }

    public abstract int[] getLocationToFireAt(Grid enemyGrid);
    public abstract void placeShips(); 
}
