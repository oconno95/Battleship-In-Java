package battleship;

public abstract class Player {

    private Grid grid;

    public Player() {
        grid = new Grid();
    }

    public Grid getGrid() {
        return grid;
    }

    public int fireAt(Player p) {
        int[] fireLocation = this.getLocationToFireAt();
        p.getGrid().fire(fireLocation[0], fireLocation[1]);
        return p.getGrid().isHit(fireLocation[0], fireLocation[1]);
    }

    public boolean lostAllShips() {

    }

    public abstract int[] getLocationToFireAt(Grid enemyGrid);
    public abstract void placeShips(); 
}
