package battleship;

public abstract class Player {

    private Grid grid;

    public Player() {
        grid = new Grid();
    }

    public Grid getGrid() {
        return grid;
    }

    public void fire() {
        int[] fireLocation = this.getLocationToFireAt();
        grid.fire(fireLocation[0], fireLocation[1]);
    }

    public abstract int[] getLocationToFireAt();
    public abstract void placeShip(); 
}
