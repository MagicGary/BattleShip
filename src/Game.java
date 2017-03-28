/**
 * Created by MagicGary on 10/19/2016.
 */
import java.util.Scanner;
public class Game {

    public static void main(String[] args)
    {
        //declare a new game board
        BattleshipBoard board;

        Scanner reader = new Scanner (System.in);

        //Initialize row, col, and turn values
        int row =-1;
        int col =-1;
        int turn =0;

        //set debug mode to default false
        boolean debug = false;

        //error handel the false input of rows and cols
        while(row <0 || col < 0 || row > 10 || col>10){

            System.out.println("Give the row of the board.");
            row = reader.nextInt();
            System.out.println("Give the column of the board.");
            col = reader.nextInt();

        }

        //create a new board
        board = new BattleshipBoard(col, row);

        //check if to use debug mode or not
        System.out.println("Do you want to run this in debug mode? y/n: ");
        String check = reader.next();

        if(check.equals( "y")){
            debug = true;
        }
        if(debug){
            //print the grid if in debug mode
            board.printGrid();
        }else{
            //print the game grid if not in debug mode
            board.printToUser();
        }

        //tell user the information about ShipsCollection
        int length = board.shipCollection.length;
        System.out.println("There are " + length + " ships,");
        System.out.print("with length ");
        for(int i =0; i< length; i++){
            System.out.print(board.shipCollection[i] + " ");
        }
        System.out.println();

        //start the game
        System.out.println("Enter x and y from 0 to length-1");
        System.out.println();

        //play the game until all ships are destroyed
        while(!board.allDestroyed()){

            //each round total turn + 1
            turn = turn +1;
            Scanner reader2 = new Scanner(System.in);
            int x = -1;
            int y = -1;

            //handle out-of-bound input while gives penalty
            while(x< 0 || x >= board.NUM_COLS || y < 0 || y >= board.NUM_ROWS) {

                System.out.println("Enter the x-coordinate.");
                x = reader2.nextInt();
                System.out.println("Enter the y-coordinate");
                y = reader2.nextInt();

                if(x< 0 || x >= board.NUM_COLS || y < 0 || y >= board.NUM_ROWS){
                    System.out.println("Out of bound");
                    turn = turn +1;
                }
            }

            //check each point before attack that point

            //if the point is already been missed
            //it's a double hit and gives penalty
            if(board.Board[x][y] == board.isMiss){
                System.out.println("Penalty");
                turn = turn +1;

            }
            //if the point is already been hit
            //it's a double hit and gives penalty
            else if (board.Board[x][y] == board.isHit){
                System.out.println("Penalty.");
                turn = turn +1;

            }//if the point is empty then you missed
            else if (board.Board[x][y] == board.isEmpty){

                System.out.println("You Missed.");

            }//if the point has a ship then you hit
            else if (board.Board[x][y] == board.isShip){
                System.out.println("You Hit.");
            }

            //after check the point attack the point
            board.attack(x,y);

            if(debug){
                board.printGrid();
            }else{
                board.printToUser();

            }

        }

        System.out.println("You win! Ad total turn is "+ turn +" times");



    }


}
