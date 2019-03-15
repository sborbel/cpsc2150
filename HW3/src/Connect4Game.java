import java.util.*;
/**
 * Connect4Game starts the game and uses the functions in GameBoard to simulate a game of Connect 4
 * Name: Sean Borbely
 * Class: CPSC2150 - HW3
 * Date: 3/15/19
 */

public class Connect4Game {
    private static final int MAX_ROWS = 100;
    private static final int MAX_COLS = 100;
    private static final int MAX_PLAYERS = 10;
    private static final int MIN_ROWS = 3;
    private static final int MIN_COLS = 3;
    private static final int MIN_PLAYERS = 2;

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

        IGameBoard board;
        List<Character> players;
        boolean inPlay = true;
        boolean startGame = true;
        boolean invalidToken = false;
        int currentCol;
        int currentPlayer = 0;
        int rows;
        int cols;
        int numToWin;
        int numPlayers;
        char tokenToAdd;
        char gameType;
        String playAgain;

        while(startGame) {

            do {
                System.out.println("How many players?");
                numPlayers = in.nextInt();
                if (numPlayers > MAX_PLAYERS) {
                    System.out.println("Must be " + MAX_PLAYERS + " players or fewer");
                }
                if (numPlayers < MIN_PLAYERS) {
                    System.out.println("Must be at least " + MIN_PLAYERS + " players");
                }
            } while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS);

            players = new LinkedList<>();

            for(int i = 0; i < numPlayers; i++){
                do {
                    invalidToken = false;
                    System.out.println("Enter the character to represent player " + (i+1));
                    tokenToAdd = Character.toUpperCase(in.next().charAt(0));

                    if (players.contains(tokenToAdd)) {
                        invalidToken = true;
                        System.out.println(tokenToAdd + " is already taken as a player token!");
                    }
                    else{
                        players.add(tokenToAdd);
                    }
                } while (invalidToken);
            }

            do {
                System.out.println("How many rows should be on the board?");
                rows = in.nextInt();
                if (rows > MAX_ROWS) {
                    System.out.println("Can have at most " + MAX_ROWS + " rows");
                }
                if (rows < MIN_ROWS) {
                    System.out.println("Must have at least 3 rows");
                }
            } while (rows < MIN_ROWS || rows > MAX_ROWS);

            do {
                System.out.println("How many columns should be on the board?");
                cols = in.nextInt();
                if (cols > MAX_COLS) {
                    System.out.println("Can have at most " + MAX_COLS + " columns");
                }
                if (cols < MIN_COLS) {
                    System.out.println("Must have at least 3 columns");
                }
            } while (cols < MIN_COLS || cols > MAX_COLS);

            do {
                System.out.println("How many in a row to win?");
                numToWin = in.nextInt();
                if (numToWin > 25) {
                    System.out.println("Can have at most 25 in a row to win");
                }
                if (numToWin < 3) {
                    System.out.println("Must have at least 3 in a row to win");
                }
            } while (numToWin < 3 );

            do {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
                gameType = in.next().charAt(0);
                if (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm') {
                    System.out.println("Invalid input. Try again.");
                }
            } while (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm');

            if(gameType == 'F' || gameType == 'f'){
                board = new GameBoard(rows, cols, numToWin);
            }
            else{
                board = new GameBoardMem(rows, cols, numToWin);
            }


            inPlay = true;

            System.out.println(board.toString());

            while (inPlay) {
                do {
                    do {
                        System.out.println("Player " + players.get(currentPlayer) + ", what column do you want to place your marker in?");
                        currentCol = in.nextInt();
                        if (currentCol < 0) {
                            System.out.println("Column cannot be less than 0");
                        } else if (currentCol >= cols) {
                            System.out.println("Column cannot be greater than " + (cols - 1));
                        }
                    } while (currentCol < 0 || currentCol >= cols);
                    if (!board.checkIfFree(currentCol)) {
                        System.out.println("Column is full");
                    }
                } while (!board.checkIfFree(currentCol) || currentCol < 0 || currentCol >= cols);

                board.placeToken(players.get(currentPlayer), currentCol);

                System.out.println(board.toString());

                if (board.checkForWin(currentCol)) {
                    System.out.println("Player " + players.get(currentPlayer) + " won!");
                    do {
                        System.out.println("Would you like to play again? Y/N");
                        playAgain = in.next();
                    }
                    while (!playAgain.equals("Y") && !playAgain.equals("y") && !playAgain.equals("N") && !playAgain.equals("n"));

                    if (playAgain.equals("Y") || playAgain.equals("y")) {
                        inPlay = false;
                        startGame = true;
                    } else if (playAgain.equals("N") || playAgain.equals("n")) {
                        inPlay = false;
                        startGame = false;
                    }
                }
                else if (board.checkTie() && !board.checkForWin(currentCol)) {
                    System.out.println("Tie game");
                    do {
                        System.out.println("Would you like to play again? Y/N");
                        playAgain = in.next();
                    }
                    while (!playAgain.equals("Y") && !playAgain.equals("y") && !playAgain.equals("N") && !playAgain.equals("n"));

                    if (playAgain.equals("Y") || playAgain.equals("y")) {
                        inPlay = false;
                        startGame = true;
                    } else if (playAgain.equals("N") || playAgain.equals("n")) {
                        inPlay = false;
                        startGame = false;
                    }
                }

                else {
                    if (currentPlayer == players.size() - 1) {
                        currentPlayer = 0;
                    } else
                        currentPlayer++;
                }
            }
        }
    }


}
