/**
 * Name: Sean Borbely
 * Class: CPSC2150 - HW3
 * Date: 3/14/19
 * GameBoardMem: Uses a Map to represent a board of player tokens. Memory-efficient.
 *
 * Invariant: board will update after each move to represent the spaces already taken up
 *            Size of the board does not change
 * Correspondence: r: rows, c: cols
 */
import java.util.*;
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    private int rows;
    private int cols;
    private Map<Integer, List<Character>> board;
    private int posToWin;
    private int numTotalMoves;

    /**
     Parameterized constructor for GameBoardMem
     @Requires ("0 < boardRows < 10 && 0 < boardCols < 10" && inARow > 0)
     @Ensures each space in board is filled with a blank space
     @Param boardRows is the number of rows for the board
     @Param boardCols is the number of columns in the board
     @Param is the number of positions a player must get in a row to win

     */
    public GameBoardMem(int boardRows, int boardCols, int inARow){
        rows = boardRows;
        cols = boardCols;
        posToWin = inARow;
        board = new HashMap<>();
        numTotalMoves = 0;
    }

    @Override
    public void placeToken(char p, int c) {
        if(board.get(c) != null) {
            board.get(c).add(p);
            numTotalMoves++;
        }
        else{
            board.put(c, new LinkedList<>());
            board.get(c).add(p);
        }
    }

    @Override
    public char whatsAtPos(int r, int c) {
        if(board.get(c) != null) {
            if(rows - r <= board.get(c).size()) {
                return board.get(c).get(rows - r - 1);
            }
            else{
                return ' ';
            }
        }
        else{
            return ' ';
        }
    }

    @Override
    public boolean checkTie() {
        int totalPosInBoard = rows * cols;
        return numTotalMoves == totalPosInBoard;
    }

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumColumns() {
        return cols;
    }

    @Override
    public int getNumToWin() {
        return posToWin;
    }
}
