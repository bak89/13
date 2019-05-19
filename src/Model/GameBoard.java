package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class GameBoard {
    private final int row;
    private final int column;
    private PropertyChangeSupport support;
    private RandomGenerator random;
    private Vector<ArrayList<Integer>> gridArray; //ho creato un array di arraylist

    /**
     * Game board do stuff
     *
     * @param row
     * @param column
     * @param random
     */
    public GameBoard(int row, int column, RandomGenerator random, int initialLevel) {
        support = new PropertyChangeSupport(this);
        this.row = row;
        this.column = column;
        this.random = random;
        this.gridArray = createNewGrid(row, column, random, initialLevel);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(getValueTile(i, j) + " ");
            }
            System.out.println();
        }
    }

    private static Vector<ArrayList<Integer>> createNewGrid(int row, int column, RandomGenerator random, int initialLevel) {//questo è il random che poi cambio in generator
        Vector<ArrayList<Integer>> grid = new Vector<>(); //nuovo array di column colonne
        for (int j = 0; j < column; j++) {
            grid.add(new ArrayList<>());

            for (int i = 0; i < row; i++) {
                grid.get(j).add(random.getRandomNumber(initialLevel));
            }
        }
        //avere numero più grande sotto
        Random randomMax = new Random();
        grid.get(randomMax.nextInt(column)).set(row - 1, initialLevel);
        return grid;
    }

    //clicktile
    public Integer getValueTile(int x, int y) {//ritorna il valore del tile in row e column
        return gridArray.get(y).get(x);//get per colonne quindi inizio per column
    }

    //ritorna val massimo del grid
    public Integer getLevel() {
        //   return gridArray.stream().map(Collections::max).max(Comparator.naturalOrder()).get();
        int max = Integer.MIN_VALUE;
        for (ArrayList<Integer> forColumn : gridArray) {
            max = Math.max(Collections.max(forColumn), max);
        }
        return max;
    }

    public Integer getValueMin() {
        int min = Integer.MAX_VALUE;
        for (ArrayList<Integer> forColumn : gridArray) {
            min = Math.min(Collections.min(forColumn), min);//problema min null exc
        }
        return min;
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
        System.out.println("row= " + x + " column= " + y);

        ArrayList<Location> neighbors = getNeighborTiles(x, y, new ArrayList<>());
        for (Location tile : neighbors) {//qui settiamo i null(creiamo i buchi)
            System.out.println("row= " + x + " column= " + y);
        }

        int oldLevel = getLevel();
        gridArray.get(y).set(x, getValueTile(x, y) + 1);//get colonna, set x di quella colonna incremento valore
        int level = getLevel();

        for (Location tile : neighbors) {//qui settiamo i null(creiamo i buchi)
            gridArray.get(tile.getY()).set(tile.getX(), null);//get del mio array, get dalla colonna e poi setta null i vicini
        }


        //non cadono ancora

        for (ArrayList<Integer> forColumn : gridArray) {//remove
            forColumn.removeIf(Objects::isNull);// è uguale a sta cosa "removeTile -> removeTile == null"
        }

        //incrementare livello, in modo che quando incrementato, i tile piccoli cadono
        if (level != oldLevel) {//se il livello è cambiato
            int min = getValueMin();
            for (ArrayList<Integer> forColumn : gridArray) {
                forColumn.removeIf(removeTile -> removeTile == min);
            }
            //   support.firePropertyChange("Level", oldLevel, level); problema
        }

        //aggiungo nuovi tile
        for (ArrayList<Integer> forColumn : gridArray) {
            while (forColumn.size() < row) {
                forColumn.add(0, random.getRandomNumber(level));
            }
        }


        //print an array in console to check the work
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(getValueTile(i, j) + " ");
            }
            System.out.println();
        }

        //event
        if (level != oldLevel) {//se il livello è cambiato
            support.firePropertyChange("Level", oldLevel, level);
        }

        support.firePropertyChange("Fall", null, null);

        if (!isClickable()) {
            support.firePropertyChange("Game Over", false, true);
        }
    }

    public boolean isClickable() {
        /*for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (!getNeighborTiles(x, y, new ArrayList<>()).isEmpty()) {
                    return true;
                }
            }
        }
        return false;*/ //non veloce

        for (int x = 0; x < row - 1; x++) {
            for (int y = 0; y < column; y++) {

                if (getValueTile(x, y).equals(getValueTile(x + 1, y))) {
                    System.out.println("x " + x + ",y " + y + " Verticale");
                    return true;
                }
            }
        }

        for (int y = 0; y < column - 1; y++) {
            for (int x = 0; x < row; x++) {
                if (getValueTile(x, y).equals(getValueTile(x, y + 1))) {
                    System.out.println("x " + x + ",y " + y + " Orizzontale");
                    return true;
                }
            }
        }
        System.out.println("morto");
        return false;
    }

    public boolean isClickable(int x,int y){
        //return !getNeighborTiles(x, y, new ArrayList<>()).isEmpty();
        Integer value = getValueTile(x, y);

        Location[] tilesNeighbor = new Location[]{
                tryLocation(x + 1, y),
                tryLocation(x - 1, y),
                tryLocation(x, y + 1),
                tryLocation(x, y - 1)
        };

        for (Location neighbor : tilesNeighbor) {
            if (neighbor == null) continue;
            if (value.equals(getValueTile(neighbor.getX(), neighbor.getY()))) {
                return true;
            }
        }
        return false;
    }

    public void bombTile(int x, int y) {

        int oldLevel = getLevel();
        gridArray.get(y).remove(x);
        gridArray.get(y).add(0, random.getRandomNumber(oldLevel));
        int level = getLevel();

        if (level != oldLevel) {//se il livello è cambiato
            support.firePropertyChange("Level", oldLevel, level);
        }
        support.firePropertyChange("Fall", null, null);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}


