import java.util.*;
/**
 * Connect4Game starts the game and uses the functions in GameBoard to simulate a game of Connect 4
 * Name: Sean Borbely
 * Class: CPSC2150 - HW1
 * Date: 2/7/19
 */

public class Connect4Game {

    private static int numCols = 7;

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

        GameBoard board = new GameBoard();
        char[] players = {'X', 'Y'};
        boolean inPlay = true;
        int currentCol;
        int currentPlayer = 0;
        String playAgain;


        while(inPlay){


            System.out.println(board.toString());
            do {
                System.out.println("Player " + players[currentPlayer] + ", what column do you want to place your marker in?");
                currentCol = in.nextInt();
                if(currentCol < 0 || currentCol >= numCols){
                    System.out.println("Enter a valid column");
                }
                else if(!board.checkIfFree(currentCol)){
                    System.out.println("Column is full");
                }
                else if(currentCol < 0){
                    System.out.println("Column cannot be less than 0");
                }
                else if(currentCol >= numCols){
                    System.out.println("Column cannot be greater than " + (numCols - 1));
                }
            }while(!board.checkIfFree(currentCol) || currentCol < 0 || currentCol >= numCols);

            board.placeToken(players[currentPlayer], currentCol);

            if(board.checkForWin(currentCol)){
                System.out.println("Player " + players[currentPlayer] + " won!");
                do {
                    System.out.println("Would you like to play again? Y/N");
                    playAgain = in.next();
                }while(!playAgain.equals("Y") && !playAgain.equals("y") && !playAgain.equals("N") && !playAgain.equals("n"));

                if(playAgain.equals("Y") || playAgain.equals("y")){
                    inPlay = true;
                }
                else if(playAgain.equals("N") || playAgain.equals("n")){
                    inPlay = false;
                }
            }
            else if(board.checkTie() && !board.checkForWin(currentCol)){
                System.out.println("Tie game");
                do {
                    System.out.println("Would you like to play again? Y/N");
                    playAgain = in.next();
                }while(!playAgain.equals("Y") && !playAgain.equals("y") && !playAgain.equals("N") && !playAgain.equals("n"));

                if(playAgain.equals("Y") || playAgain.equals("y")){
                    inPlay = true;
                }
                else if(playAgain.equals("N") || playAgain.equals("n")){
                    inPlay = false;
                }
            }
            else{
                if(currentPlayer == players.length-1){
                    currentPlayer = 0;
                }
                else
                    currentPlayer++;
            }
        }
    }
}
