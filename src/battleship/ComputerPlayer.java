package battleship;

public class ComputerPlayer extends Player {
    int difficulty;

    public ComputerPlayer() {this(0);}
    public ComputerPlayer(int difficulty) {
        this.difficulty = difficulty;
        name = "Computer";
    }
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        int x = -1;
        int y = -1;

        if (difficulty == 0) {
            do {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);
            } while(!enemyGrid.canFireAt(x, y));
        }
        else if (difficulty == 1) {
            outerloop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (enemyGrid.getCell(i, j) == Grid.HIT_SHIP) {
                        for (int r = 0; r < 4; r++) {
                            if (r == 0) {
                                x = i+1;
                                y = j;
                            }
                            else if (r == 1) {
                                x = i-1;
                                y = j;
                            }
                            else if (r == 2) {
                                x = i;
                                y = j+1;
                            }
                            else {
                                x = i;
                                y = j-1;
                            }
                            
                            if ((enemyGrid.pointIsOnGrid(x, y) && enemyGrid.canFireAt(x, y))) break outerloop;
                        }
                    }
                }
            }
            if (x == -1) {
                do {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                } while(!enemyGrid.canFireAt(x, y));
            }
        }
        else if (difficulty == 2) {
            outerloop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (enemyGrid.getCell(i, j) == Grid.HIT_SHIP) {
                        for (int r = 0; r < 4; r++) {
                            if (r == 0) {
                                x = i+1;
                                y = j;
                            }
                            else if (r == 1) {
                                x = i-1;
                                y = j;
                            }
                            else if (r == 2) {
                                x = i;
                                y = j+1;
                            }
                            else {
                                x = i;
                                y = j-1;
                            }
                            
                            if ((enemyGrid.pointIsOnGrid(x, y) && enemyGrid.canFireAt(x, y))) break outerloop;
                        }
                    }
                }
            }
            randomPoint:
            if (x == -1) {
                for (int i = 0; i < 10; i++) {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);

                    if ((!enemyGrid.pointIsOnGrid(x+1, y) || enemyGrid.canFireAt(x+1, y)) &&
                        (!enemyGrid.pointIsOnGrid(x-1, y) || enemyGrid.canFireAt(x-1, y)) &&
                        (!enemyGrid.pointIsOnGrid(x, y+1) || enemyGrid.canFireAt(x, y+1)) &&
                        (!enemyGrid.pointIsOnGrid(x, y-1) || enemyGrid.canFireAt(x, y-1)) && 
                        (enemyGrid.canFireAt(x, y))) {
                            break randomPoint;
                    }
                }
                
                do {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                } while(!enemyGrid.canFireAt(x, y));
            }
        }
        
        System.out.println("Computer Guess: " + y + ", " + x);
        return new int[] {x, y};
    }

    private int[] getPositionsOfNShip(int length) {
        int x0, y0, x1, y1;
        x0 = (int) (Math.random() * 10);
        y0 = (int) (Math.random() * 10);

        // 0 is right, 1 is up, 2 is left, 3 is down
        int direction = (int) (Math.random() * 4);

        switch(direction) {
            //right
            case 0:
                x1 = x0 + length - 1;
                y1 = y0;
                break;
            //up
            case 1:
                x1 = x0;
                y1 = y0 - length + 1;
                break;
            //left
            case 2:
                x1 = x0 - length + 1;
                y1 = y0;
                break;
            //down
            default:
                x1 = x0;
                y1 = y0 + length - 1;
                break;
        }
        return new int[] {x0, y0, x1, y1};
    }
    @Override
    public boolean placeDestroyer() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(2);
        } while(!this.getGrid().placeDestroyer(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        return true;
    }
    @Override
    public boolean placeCruiser() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(3);
        } while(!this.getGrid().placeCruiser(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        return true;
    }
    @Override
    public boolean placeSubmarine() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(3);
        } while(!this.getGrid().placeSubmarine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        return true;

    }
    @Override
    public boolean placeBattleship() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(4);
        } while(!this.getGrid().placeBattleship(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        return true;

    }
    @Override
    public boolean placeCarrier() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(5);
        } while(!this.getGrid().placeCarrier(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        return true;

    }

}