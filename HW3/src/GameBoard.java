/**
 * Name: Sean Borbely
 * Class: CPSC2150 - HW3
 * Date: 3/15/19
 * Invariant: board and positionStatus will update after each move to represent the spaces already taken up.
 *            Size of the board does not change.
 * Correspondence: r: rows, c: cols
 */

public class GameBoard extends AbsGameBoard implements IGameBoard{
    private int rows;
    private int cols;
    private char[][] board;
    private boolean[][] positionStatus; //False if empty
    private int positionsToWin;

    /**
     Parameterized constructor for GameBoard
     @Requires (0 < boardRows < 10 && 0 < boardCols < 10 && inARow > 0)
     @Ensures each space in board is filled with a blank space
     @Param boardRows is the number of rows for the board
     @Param boardCols is the number of columns in the board
     @Param is the number of positions a player must get in a row to win

     */
    public GameBoard(int boardRows, int boardCols, int inARow){
        rows = boardRows;
        cols = boardCols;
        positionsToWin = inARow;
        board = new char[rows][cols];
        positionStatus = new boolean[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = ' ';
                positionStatus[i][j] = false;

            }
        }
    }

    public void placeToken(char p, int c){
        int openRow = 0;
        int r = rows - 1;
        while(positionStatus[r][c] == true){
            r--;
        }

        openRow = r;


        board[openRow][c] = p;
        positionStatus[openRow][c] = true;

    }

    public char whatsAtPos(int r, int c){
        return board[r][c];
    }

    public boolean checkTie(){
        int emptySpots = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(positionStatus[i][j] == false){
                    emptySpots++;
                }
            }
        }
        if(emptySpots == 0){
            return true;
        }
        else{
            return false;
        }

    }


    public int getNumRows(){
        return rows;
    }

    public int getNumColumns(){
        return cols;
    }

    public int getNumToWin(){
        return positionsToWin;
    }



}
