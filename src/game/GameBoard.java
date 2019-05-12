package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GameBoard {
    private final int row;
    private final int column;
    private Random random = new Random();
    private Vector<ArrayList<Integer>> gridArray; //ho creato un array di arraylist

    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        this.gridArray = createNewGrid(row, column, random);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(getValueTile(i, j) + " ");
            }
            System.out.println();
        }
    }

    private static Vector<ArrayList<Integer>> createNewGrid(int row, int column, Random random) {//questo è il random che poi cambio in generator
        Vector<ArrayList<Integer>> grid = new Vector<>(); //nuovo array di column colonne
        for (int j = 0; j < column; j++) {
            grid.add(new ArrayList<>());

            for (int i = 0; i < row; i++) {
                grid.get(j).add(random.nextInt(4) + 1);
            }
        }
        return grid;
    }

    //click tile


    public Integer getValueTile(int x, int y) {//ritorna il valore del tile in row e column
        return gridArray.get(y).get(x);//get per colonne quindi inizio per column
    }

    private Location tryLocation(int x, int y) {//non crea problemi se fuori
        if (x < 0) return null;
        if (x >= row) return null;
        if (y < 0) return null;
        if (y >= column) return null;//se sono fuori da array=null
        return new Location(x, y);
    }

    private ArrayList<Location> getNeighborTiles(int x, int y, ArrayList<Location> visitedTile) {
        ArrayList<Location> sameNumber = new ArrayList<>();
        Integer value = getValueTile(x, y);
        Location tile = tryLocation(x, y);

        assert tile != null;//utente che cliccca fuori exception in debug

        visitedTile.add(tile);//mi salva i tile visitati

        //search tile neighbor with position recursion
        Location[] tilesNeighbor = new Location[]{
                tryLocation(x + 1, y),
                tryLocation(x - 1, y),
                tryLocation(x, y + 1),
                tryLocation(x, y - 1)
        };

        for (Location neighbor : tilesNeighbor) {
            if (neighbor == null) continue;
            if (value.equals(getValueTile(neighbor.getX(), neighbor.getY())) && !visitedTile.contains(neighbor)) {
                sameNumber.add(neighbor);
                sameNumber.addAll(getNeighborTiles(neighbor.getX(), neighbor.getY(), visitedTile));
            }
        }
        return sameNumber;
    }

    public void playTile(int x, int y) {
        //isClickable();
        System.out.println("row= " + x + " column= " + y);

        ArrayList<Location> neighbors = getNeighborTiles(x, y, new ArrayList<>());
        for (Location tile : neighbors) {//qui settiamo i null(creiamo i buchi)
            System.out.println("row= " + x + " column= " + y);
        }

        if (neighbors.isEmpty()) {
            return;
        }

        for (Location tile : neighbors) {//qui settiamo i null(creiamo i buchi)
            gridArray.get(tile.getY()).set(tile.getX(), null);//get del mio array, get dalla colonna e poi setta null i vicini
        }
        gridArray.get(y).set(x, getValueTile(x, y) + 1);//get colonna, set x di quella colonna incremento valore

        //non cadono ancora


        //print an array in console to check the work
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(getValueTile(i, j) + " ");
            }
            System.out.println();
        }
    }
}



   /* public boolean isClickable() {
       if row,column has neighbor then
       return true

        return true;
    }*/


/*





    private void moveTiles() {
        System.out.println("test");
        for (int i = gridArray.length - 1; i >= 0; i--) {
            for (int j = gridArray[i].length - 2; j >= 0; j--) {
                if (gridArray[i][j] != null) {
                    while (gridArray[i][j + 1] == null) {
                        gridArray[i][j].setTileY(j + 1);
                        gridArray[i][j + 1] = gridArray[i][j];
                        gridArray[i][j] = null;
                        if (j < gridArray[i].length - 2) {
                            j++;
                        }
                    }
                }
            }
        }
    }


    public void playTile(int row, int column) {
        //isClickable();
        System.out.println("row= " + row + " column= " + column);

        ArrayList<Tile> neighbors = getNeighborTiles(row, column, new ArrayList<>());
        if (neighbors.isEmpty()) {
            return;
        }

        for (Tile tile : neighbors) {//qui abbiamo anche i buchi

            gridArray[tile.getTileX()][tile.getTileY()].setValue(13);
        }

        getTile(row, column).increaseTile();//from the class tile
        //moveTiles();

        //print an array in console to check the work
        for (int i = 0; i < Settings.WIDTH; i++) {
            for (int j = 0; j < Settings.HEIGHT; j++) {
                System.out.print(this.gridArray[j][i].getValue() + " ");
            }
            System.out.println();
        }
    }*/

/**
 * Nuovo
 */



