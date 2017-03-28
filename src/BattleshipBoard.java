/**
 * This class represents a Battleship game board.
 * Structure inspired by UNC COMP 110-003 Fall 2008 Program 4 Homework Outline.
 */

import java.util.Random;
import java.lang.*;


public class BattleshipBoard {

    public static final int OUT_OF_BOUNDS = -1;

    // values to be used in the 2D grid
    public int isEmpty = 0;
    public int isShip = 1;
    public int isHit = 2;
    public int isMiss = 3;

    // directions used for shape placement
    public int isRight = 0;
    public int isDown = 1;

    // the number of rows and columns in this grid
    public int NUM_ROWS;
    public int NUM_COLS;

    // declare the 2D array
    public int[][] Board;

    // delcare the ship collection
    public int[] shipCollection;

    // all possible ship sizes
    public int[] shipSize1 = new int[]{2};
    public int[] shipSize2 = new int[]{2, 3};
    public int[] shipSize3 = new int[]{2, 3, 3};
    public int[] shipSize4 = new int[]{2, 3, 3, 4};
    public int[] shipSize5 = new int[]{2, 3, 3, 4, 5};


    /**
     * Creates a new BattleshipBoard instance and randomly places
     * ships on the grid.
     */
    public BattleshipBoard(int numCols, int numRows) {
        NUM_COLS = numCols;
        NUM_ROWS = numRows;

        // initialize the grid and each cell to empty
        initializeGrid();

        // initialize ships based on the board size
        initializeShips();

        // randomly place all of the ships on the grid
        placeAllShips();
    }

    /**
     * Initializes the game grid to size [numCol][numRow]
     * and sets each element of the grid to 0.
     */
    public void initializeGrid() {

        Board = new int[NUM_COLS][NUM_ROWS];

        for (int i = 0; i < NUM_COLS; i++) {

            for (int j = 0; j < NUM_ROWS; j++) {

                Board[i][j] = isEmpty;

            }
        }

    }

    /**
     * Initializes the shipCollection based on the size of the game board.
     */
    public void initializeShips() {

        if (Math.min(NUM_COLS, NUM_ROWS) == 2) {
            shipCollection = shipSize1;
        } else if (Math.min(NUM_COLS, NUM_ROWS) > 2 && Math.min(NUM_COLS, NUM_ROWS) <= 4) {
            shipCollection = shipSize2;
        } else if (Math.min(NUM_COLS, NUM_ROWS) > 4 && Math.min(NUM_COLS, NUM_ROWS) <= 6) {
            shipCollection = shipSize3;
        } else if (Math.min(NUM_COLS, NUM_ROWS) > 6 && Math.min(NUM_COLS, NUM_ROWS) <= 8) {
            shipCollection = shipSize4;
        } else {
            shipCollection = shipSize5;
        }
    }


    /**
     * Places all of the  ships onto the board.
     */
    private void placeAllShips() {
        for (int i = 0; i < shipCollection.length; i++) {

            placeAShip(shipCollection[i]);

        }
    }


    private void placeAShip(int size) {

        Random rnd = new Random();
        boolean flag = true;

        while (flag) {
            flag = false;
            int direction = rnd.nextInt(2);

            if (direction == isRight) {
                int x;

                if(NUM_COLS - size == 0){
                    x = 0;
                }else{
                    x = rnd.nextInt(NUM_COLS - size);

                }

                int y = rnd.nextInt(NUM_ROWS);

                for (int i = 0; i < size; i++) {

                    if (Board[x + i][y] != 0) {

                        flag = true;
                    }
                }

                if (!flag) {

                    for (int i = 0; i < size; i++) {

                        Board[x + i][y] = 1;
                    }


                }

            } else if (direction == isDown) {

                int y;

                int x = rnd.nextInt(NUM_COLS);

                if(NUM_ROWS - size ==0){
                    y = 0;
                }else{
                    y = rnd.nextInt(NUM_ROWS - size);
                }

                for (int i = 0; i < size; i++) {

                    if (Board[x][y + i] != 0) {

                        flag = true;
                    }
                }

                if (!flag) {

                    for (int i = 0; i < size; i++) {

                        Board[x][y + i] = 1;
                    }
                }
            }
        }
    }


    /**
     * Attacks the grid cell at the specified row and column.
     * If the grid cell contains:
     * - SHIP: the value of the cell is set to HIT
     * - HIT: the value of the cell does not change
     * Otherwise, the value of the cell is set to MISS.
     * <p>
     * This method returns true if the attack resulted in a ship being hit,
     * and false otherwise.
     * <p>
     * Note: this method also returns true if a cell that has already
     * been hit is attacked.
     * @return true if the attack results in a ship being hit (even
     * if the ship at that cell has already been hit),
     * false otherwise
     */
    public boolean attack(int col, int row) {
        // ::: FILL IN THIS CODE
        if (Board[col][row] == isShip) {

            Board[col][row] = isHit;
            return true;

        } else if (Board[col][row] == isHit) {

            return true;

        } else {
            Board[col][row] = isMiss;
            return false;
        }
    }


    /**
     * Returns true if all of the ships have been destroyed, and
     * false otherwise.
     *
     * @return true if all ships have been destroyed, false otherwise
     */
    public boolean allDestroyed() {

        for (int i = 0; i < NUM_ROWS; i++) {

            for (int j = 0; j < NUM_COLS; j++) {

                if (Board[j][i] == isShip) {
                    return false;
                }
            }
        }

        return true;
    }




    /**
     * Prints the grid.
     */
    public void printGrid() {

        for (int i = 0; i < NUM_ROWS; i++) {

            for (int j = 0; j < NUM_COLS; j++) {

                System.out.printf("%3d", Board[j][i]);
            }
            System.out.println();
        }
    }

    public void printToUser(){

        for (int i = 0; i < NUM_ROWS; i++) {

            for (int j = 0; j < NUM_COLS; j++) {

                if(Board[j][i] == isEmpty|| Board[j][i] == isShip){
                    System.out.print("- ");
                }else if (Board[j][i] == isHit){
                    System.out.print("x ");
                }else if(Board[j][i] == isMiss){
                    System.out.print("/ ");
                }
            }
            System.out.println();
        }


    }

}