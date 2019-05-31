package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class GameBoard {
    private final int row;
    private final int column;
    private PropertyChangeSupport support;
    private RandomGenerator random;
    private Stack<Vector<ArrayList<Integer>>> undoStack = new Stack<>(); //create stack of array of arrayList

    /**
     * Create a new Game Board
     *
     * @param row          : row
     * @param column       : column
     * @param random       : Random generator for tile
     * @param initialLevel : first level
     */
    public GameBoard(int row, int column, RandomGenerator random, int initialLevel) {
        support = new PropertyChangeSupport(this);
        this.row = row;
        this.column = column;
        this.random = random;
        this.undoStack.push(createNewGrid(row, column, random, initialLevel));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(getValueTile(i, j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Create a new Grid to add at GameBoard
     *
     * @param row          : row
     * @param column       : column
     * @param random       : Random generator for tile
     * @param initialLevel : first level
     * @return new grid
     */
    private static Vector<ArrayList<Integer>> createNewGrid(int row, int column, RandomGenerator random, int initialLevel) {//questo è il random che poi cambio in generator

        //new Vector of column, for column add an ArrayList
        Vector<ArrayList<Integer>> grid = new Vector<>();
        for (int j = 0; j < column; j++) {
            grid.add(new ArrayList<>());

            for (int i = 0; i < row; i++) {
                grid.get(j).add(random.getRandomNumber(initialLevel));
            }
        }
        /* have the biggest number down */
        Random randomMax = new Random();
        grid.get(randomMax.nextInt(column)).set(row - 1, initialLevel);
        return grid;
    }

    /**
     * @return gridArray
     */
    private Vector<ArrayList<Integer>> getGridArray() {
        return undoStack.peek();
    }

    /**
     * Undo?
     *
     * @return true if the can do the Undo
     */
    public boolean canUndo() {
        return undoStack.size() > 1;
    }

    public void doUndo() {
        undoStack.pop();
        support.firePropertyChange("Fall", null, null);
    }

    /**
     * Save the state in the Stack
     */
    private void saveState() {
        //new Vector of column, for column add an ArrayList
        Vector<ArrayList<Integer>> grid = new Vector<>();
        for (int j = 0; j < column; j++) {
            grid.add(new ArrayList<>());

            for (int i = 0; i < row; i++) {
                grid.get(j).add(getGridArray().get(j).get(i));
            }
        }
        undoStack.push(grid);

    }

    /**
     * Value Tile
     *
     * @param x : row
     * @param y : column
     * @return value of tile in row and column
     */
    public Integer getValueTile(int x, int y) {
        return getGridArray().get(y).get(x);//Vector is per column, so getColumn first
    }

    /**
     * Return the level
     *
     * @return the biggest value in our grid
     */
    public Integer getLevel() {
        // another way to do it with stream
        // return gridArray.stream().map(Collections::max).max(Comparator.naturalOrder()).get();
        int max = Integer.MIN_VALUE;
        for (ArrayList<Integer> forColumn : getGridArray()) {
            if (forColumn.isEmpty()) continue;
            max = Math.max(Collections.max(forColumn), max);
        }
        return max;
    }

    /**
     * Return the smallest Value
     *
     * @return min
     */
    public Integer getValueMin() {
        int min = Integer.MAX_VALUE;
        for (ArrayList<Integer> forColumn : getGridArray()) {
            if (forColumn.isEmpty()) continue;
            min = Math.min(Collections.min(forColumn), min);
        }
        return min;
    }

    /**
     * Know if tile is in the grid, if out no problem
     *
     * @param x : row
     * @param y : column
     * @return the location of tile
     */
    private Location tryLocation(int x, int y) {
        if (x < 0) return null;
        if (x >= row) return null;
        if (y < 0) return null;
        if (y >= column) return null;
        return new Location(x, y);
    }

    /**
     * Check if tile have neighbor
     *
     * @param x           : row
     * @param y           : column
     * @param visitedTile : save the visited tile in ArrayList
     * @return if value tile are the same
     */
    private ArrayList<Location> getNeighborTiles(int x, int y, ArrayList<Location> visitedTile) {
        ArrayList<Location> sameNumber = new ArrayList<>();
        Integer value = getValueTile(x, y);
        Location tile = tryLocation(x, y);

        //exception in debug click out grid
        assert tile != null;

        //save visited tile
        visitedTile.add(tile);

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

    /**
     * Check if tile is clickable and tell where in grid (For CONSOLE)
     *
     * @return if is clickable or not
     */
    public boolean isClickable() {
        /*for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (!getNeighborTiles(x, y, new ArrayList<>()).isEmpty()) {
                    return true;
                }
            }
        }
        return false;*/ //other way easy to write but not fast

        for (int x = 0; x < row - 1; x++) {
            for (int y = 0; y < column; y++) {

                if (getValueTile(x, y).equals(getValueTile(x + 1, y))) {
                    System.out.println("x " + x + ",y " + y + " Vertical");
                    return true;
                }
            }
        }

        for (int y = 0; y < column - 1; y++) {
            for (int x = 0; x < row; x++) {
                if (getValueTile(x, y).equals(getValueTile(x, y + 1))) {
                    System.out.println("x " + x + ",y " + y + " Horizontal");
                    return true;
                }
            }
        }
        System.out.println("Dead!");
        return false;
    }

    /**
     * Check if tile is clickable and tell where in grid
     *
     * @param x : row
     * @param y : column
     * @return if is clickable or not
     */
    public boolean isClickable(int x, int y) {
        //another way
        // return !getNeighborTiles(x, y, new ArrayList<>()).isEmpty();
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

    /**
     * Remove tile (bomb) , and add another random Tile
     */
    public void bombTile(int x, int y) {
        saveState();
        int oldLevel = getLevel();
        getGridArray().get(y).remove(x);
        getGridArray().get(y).add(0, random.getRandomNumber(oldLevel));
        int level = getLevel();

        //if level change
        if (level != oldLevel) {
            support.firePropertyChange("Level", oldLevel, level);
        }
        support.firePropertyChange("Fall", null, null);
    }

    /**
     * All the game logic
     *
     * @param x : row
     * @param y : column
     */
    public void playTile(int x, int y) {
        saveState();
        System.out.println("row= " + x + " column= " + y);

        ArrayList<Location> neighbors = getNeighborTiles(x, y, new ArrayList<>());
        //set null to create the holes
        for (Location tile : neighbors) {
            System.out.println("row= " + x + " column= " + y);
        }

        int oldLevel = getLevel();
        //get column, set x of that column and increase value
        getGridArray().get(y).set(x, getValueTile(x, y) + 1);
        int level = getLevel();

        for (Location tile : neighbors) {//create holes
            //get gridArray, get from column then set null the neighbors
            getGridArray().get(tile.getY()).set(tile.getX(), null);
        }

        //until here not fall yet

        //remove if tile = null
        for (ArrayList<Integer> forColumn : getGridArray()) {
            forColumn.removeIf(Objects::isNull); //same to "removeTile -> removeTile == null"
        }

        // when level up, remove all the smallest tile
        if (level != oldLevel) { //if level has changed
            int min = getValueMin();
            for (ArrayList<Integer> forColumn : getGridArray()) {
                forColumn.removeIf(removeTile -> removeTile == min);
            }
            //support.firePropertyChange("Level", oldLevel, level); //problem?
        }

        //add new tile
        for (ArrayList<Integer> forColumn : getGridArray()) {
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
        if (level != oldLevel) {//if the level has changed
            support.firePropertyChange("Level", oldLevel, level);
        }

        support.firePropertyChange("Fall", null, null);

        if (!isClickable()) {//if isn't clickable = game over
            support.firePropertyChange("Game Over", false, true);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}