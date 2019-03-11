/**
 * Name: Sean Borbely
 * Class: CPSC2150 - HW1
 * Date: 2/7/19
 * Invariant: board and positionStatus will update after each move to represent the spaces already taken up
 */
public class GameBoard {


    private int rows;
    private int cols;
    private char[][] board;
    private boolean[][] positionStatus; //False if empty
    private int positionsToWin;
    private int MAX_POSITIONS;

    /**
     Constructor for GameBoard
     @Requires
     @Ensures each space in board is filled with a blank space and positionStatus is created to be false in each position
     @Param boardRows is the number of rows for the board
     @Param boardCols is the number of columns in the board
     @Param is the number of positions a player must get in a row to win

     */
    public GameBoard(){
        rows = 6;
        cols = 7;
        positionsToWin = 4;
        board = new char[rows][cols];
        positionStatus = new boolean[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = ' ';
                positionStatus[i][j] = false;

            }
        }
    }

    /**
    Constructor for GameBoard
    @Requires ("0 < boardRows < 10 && 0 < boardCols < 10" && inARow > 0)
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

    /**
     * Checks if a move is valid
     * @pre-condition c > 0 && c < cols
     * @post-condition Returns if a player can put a marker in column col
     * @param c column to check if any positions are free in
     * @return True if at least the top spot is open
     */
    public boolean checkIfFree(int c){
        if(positionStatus[0][c] == true ) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Places a token after the player has chosen the next move
     * @Pre-Condition p == [player's token] && 0 <= c <= cols && checkIfFree(c)
     * @Post-condition Token is placed in the highest position in the column on top of any other tokens
     * @param p Current player's token
     * @param c Column value of the current turn
     */
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

    /**
     * Returns the value of the position in question
     * @Precondition 0 <= r < rows && 0 <= c < cols
     * @Postcondition Returns a space if position hasn't been filled, or the character's token who places a marker if it's taken
     * @param r row value to be searched
     * @param c column value to be searched
     * @return Value at that position in the board
     */
    public char whatsAtPos(int r, int c){
        return board[r][c];
    }

    /**
     * Checks if the last move resulted in a win
     * @Precondition 0 <= c < cols
     * @param c column of the last move
     * @return True iff a win has resulted from the last move
     */
    public boolean checkForWin(int c){
        int currRow = 0;
        int currCol = c;
        char currPlayer = whatsAtPos(currRow, currCol);
        boolean notFound = true;
        for(int i = 0; i < rows && notFound; i++){
            if(positionStatus[i][c] == true){
                currRow = i;
                currCol = c;
                currPlayer = whatsAtPos(currRow, currCol);
                notFound = false;
            }
        }
        if(checkDiagonalWin(currRow, currCol, currPlayer)){//Checks if there is a Diagonal win after last move
            clearBoard();
            return true;
        }

        else if(checkHorizontalWin(currRow, currCol, currPlayer)){  //Checks if there is a Horizontal win after last move
            clearBoard();
            return true;
        }
        else if(checkVerticalWin(currRow, currCol, currPlayer)){    //Checks if there is a Vertical win after last move
            clearBoard();
            return true;
        }
        else{                                  //Returns false if no win
            return false;
        }


    }

    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same row
     */
    private boolean checkHorizontalWin(int r, int c, char p) {
    //checks to see if the last marker placed resulted in 5 in a row horizontally. Returns true if it does, otherwise false
        int currCol = c;

        int count = 0;            //Counter for consecutive pieces in a row
        boolean consecutive = true;


        while (currCol < cols && consecutive) {
            if (board[r][currCol] == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            currCol++;
        }

        currCol = c - 1;
        consecutive = true;

        while (currCol >= 0 && consecutive) {
            if (board[r][currCol] == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            currCol--;
        }

        return (count >= positionsToWin);
    }

    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same column
     */
    private boolean checkVerticalWin(int r, int c, char p) {
//checks to see if the last marker placed resulted in 5 in a row vertically. Returns true if it does, otherwise false
        int currRow = r; //Placeholder for Row Value
        int count = 0;            //Counter for consecutive pieces in a row
        boolean consecutive = true;


        while (currRow < rows && consecutive) {
            if (board[currRow][c] == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            currRow++;
        }

        currRow = r - 1;
        consecutive = true;

        while (currRow >= 0 && consecutive) {
            if (board[currRow][c] == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            currRow--;
        }

        return (count >= positionsToWin);
    }
    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same diagonal, bottom left to top right or top left to bottom right
     */
    private boolean checkDiagonalWin(int r, int c, char p) {
//checks to see if the last marker placed resulted in 5 in a row diagonally. Returns true if it does, otherwise false
//Note: there are two diagonals to check }
        int xMove = c;  //Holds value for column from lastPos
        int yMove = r;     //Holds value for row from lastPos
        int startX;                       //Placeholder for col value for position to start search at
        int startY;                       //Placeholder for col value for position to start search at

        int difference;                //Helps to find search start position when searching NW to SE
        int sum;                       //Helps to find search start position when searching NE to SW
        int col;                       //Value for current column being searched
        int row;                       //Value for current row being searched
        int NWtoSEcount = 0;                 //Counter for positions in a row
        boolean NWtoSE = false;
        int NEtoSWcount = 0;
        boolean NEtoSW = false;

        //top left to bottom right

        //Sets Start X and Y positions
        if (xMove > yMove) {                 //If column value is greater than row value

            difference = xMove - yMove;    //Finds Search start column by taking the difference between the column and row
            startX = difference;
            startY = 0;                    //Row is set equal to zero
        } else if (xMove == yMove) {           //If column == row
            difference = xMove - yMove;
            startX = difference;           //Search start position is set to [0][0] (Middle diag)
            startY = difference;
        } else {                              //When row value is greater than column value
            difference = yMove - xMove;
            startY = difference;           //Search start row is set to the difference
            startX = 0;                    //Search start column set to 0
        }

        //counts how many X's in a row starting from start X and y
        col = startX;                                                          //col given initial value of Search start column
        for (int y = startY; (y < rows && col < cols); y++) {                    //Increments row and column value until either goes past set board size

            if (board[y][col] == p)                           //Increments count when a valid space is found
                NWtoSEcount++;
            else if (NWtoSEcount < positionsToWin && (board[y][col] != p))       //Resets count to 0 if invalid space is found
                NWtoSEcount = 0;
            col++;                                                             //Increases column value each pass through
        }

        if (NWtoSEcount >= positionsToWin) {     //Sets boolean to true if 5 in a row was found
            NWtoSE = true;
        }


        //top right to bottom left
        startX = xMove;          //Resets starting position to column value given by BoardPosition
        startY = yMove;          //Resets starting position to row value given by BoardPosition
        sum = xMove + yMove;     //Adds row and column value to assist in finding search start position

        //Sets Start X and Y positions

        if (sum > cols - 1) {               //If sum is greater than 7, we must increment column and decrement row until column == 7;
            while (startX != cols - 1) {
                startX++;
                startY--;
            }

        } else {                     //When sum <= 7 search start column = sum, row = 0

            startX = sum;
            startY = 0;
        }

        //counts how many X's in a row starting from start X and y. Must decrement cols and increment rows 1 by 1
        row = startY;                                                       //col given initial value of Search start column
        for (int x = startX; (x >= 0 && row < rows); x--) {                   //Increments row and decrements column value until either goes past set board size

            if (board[row][x] == p)                        //Increments count when a valid space is found
                NEtoSWcount++;
            else if (NEtoSWcount < positionsToWin && (board[row][x] != p))    //Resets count to 0 if invalid space is found
                NEtoSWcount = 0;
            row++;                                                          //Increases row value each pass through
        }

        if (NEtoSWcount >= positionsToWin)            //Sets boolean to true if 5 in a row was found
            NEtoSW = true;

        return (NEtoSW || NWtoSE); //returns true if both or either booleans are true and false otherwise


    }

    /**
     * @Precondition board is initialized
     * @return board in string representation
     */
    public String toString(){

        String boardOutline = "|"; //Column numbers

        for(int c = 0; c < cols; c++){
            if(c <= 8)
                boardOutline += c + "|";
            else
                boardOutline += c + "|";
        }

        boardOutline += "\n";
        for (int i = 0; i < rows; i++) {

            boardOutline += "|";
            for (int j = 0; j < cols; j++) {
                boardOutline += board[i][j] + "|";
            }
            boardOutline += "\n";
        }
        boardOutline += "\n\n";


        return boardOutline;
    }


    /**
     * @Precondition board has been initialized
     * @return true iff all spots have been taken
     */
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
            clearBoard();
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Clears the board after game has ended
     */
    private void clearBoard(){
        char[][] newBoard = new char[rows][cols];
        boolean[][] newPosStat = new boolean[rows][cols];
        board = newBoard;
        positionStatus = newPosStat;
    }




}