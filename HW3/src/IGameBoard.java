/**
 * Name: Sean Borbely
 * Class: CPSC2150 - HW3
 * Date: 3/15/19
 *
 * Interface for GameBoard which represents a connect 4 game board. No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x NUM_COLS in size. Indexing of the gameboard
 * starts in the top left corner with 0,0 and goes to NUM_ROWS-1, NUM_COLS-1.
 *
 * Defines: c: z r: z p: letter
 *
 * Constraints: MIN_COLUMNS <= c <= MAX_COLUMNS, MIN_ROWS <= r <= MAX_ROWS
 *
 * Initialization ensures: All positions in GameBoard are empty
 */


public interface IGameBoard {
    int MAX_ROWS = 100;
    int MAX_COLS = 100;
    int MIN_ROWS = 3;
    int MIN_COLS = 3;

    /**
     * Checks if a move is valid
     * @pre-condition c > 0 && c < cols
     * @post-condition Returns if a player can put a marker in column col
     * @param c column to check if any positions are free in
     * @return True if at least the top spot is open
     */
    default boolean checkIfFree(int c){
        if(whatsAtPos(0, c) != ' ' ) {
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
    void placeToken(char p, int c);

    /**
     * Returns the value of the position in question
     * @Precondition 0 <= r < rows && 0 <= c < cols
     * @Postcondition Returns a space if position hasn't been filled, or the character's token who places a marker if it's taken
     * @param r row value to be searched
     * @param c column value to be searched
     * @return Value at that position in the board
     */
    char whatsAtPos(int r, int c);

    /**
     * Checks if the last move resulted in a win diagonally, horizontally, or vertically
     * @Precondition 0 <= c < cols
     * @param c column of the last move
     * @return True iff a win has resulted from the last move
     */
    default boolean checkForWin(int c){
        int currRow = 0;
        int currCol = c;
        char currPlayer = whatsAtPos(currRow, currCol);
        boolean notFound = true;
        for(int i = 0; i < getNumRows() && notFound; i++){
            if(whatsAtPos(i, c) != ' '){
                currRow = i;
                currCol = c;
                currPlayer = whatsAtPos(currRow, currCol);
                notFound = false;
            }
        }

        if(checkDiagonalWin(currRow, currCol, currPlayer)){//Checks if there is a Diagonal win after last move
            return true;
        }

        else if(checkHorizontalWin(currRow, currCol, currPlayer)){  //Checks if there is a Horizontal win after last move
            return true;
        }
        else if(checkVerticalWin(currRow, currCol, currPlayer)){    //Checks if there is a Vertical win after last move
            return true;
        }
        else{//Returns false if no win
            return false;
        }
    }

    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @Precondition Marker has already been placed
     * @Postcondition all possible positions around last position have been checked to
     *                see if a win has resulted horizontally
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same row
     */
    default boolean checkHorizontalWin(int r, int c, char p) {

        //checks to see if the last marker placed resulted in (positionsInARow) in a row horizontally. Returns true if it does, otherwise false

        int y = r; //Placeholder for Row Value
        int x = c; //Placeholder for Column value
        int count = 0;            //Counter for consecutive pieces in a row
        boolean consecutive = true;


        while (x < getNumColumns() && consecutive) {
            if (whatsAtPos(y, x) == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            x++;
        }

        x = c - 1;
        consecutive = true;

        while (x >= 0 && consecutive) {
            if (whatsAtPos(y, x) == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            x--;
        }

        return (count >= getNumToWin());
    }

    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @Postcondition all possible positions around last position have been checked to
     *                see if a win has resulted vertically
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same column
     */
    default boolean checkVerticalWin(int r, int c, char p) {
        //checks to see if the last marker placed resulted in 5 in a row vertically. Returns true if it does, otherwise false
        int y = r; //Placeholder for Row Value
        int x = c; //Placeholder for Column value
        int count = 0;            //Counter for consecutive pieces in a row
        boolean consecutive = true;


        while (y < getNumRows() && consecutive) {
            if (whatsAtPos(y, x) == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            y++;
        }

        y = r - 1;
        consecutive = true;

        while (y >= 0 && consecutive) {
            if (whatsAtPos(y, x) == p) { //Increments count when a space has the same value as lastPos
                count++;
            } else
                consecutive = false;
            y--;
        }

        return (count >= getNumToWin());
    }

    /**
     * @Precondition 0 <= r < rows && 0 <= c < cols && p == [current player's token]
     * @Postcondition all possible positions around last position have been checked to
     *                see if a win has resulted diagonally
     * @param r row value of the last move
     * @param c column value of the last move
     * @param p character's token to be searched for
     * @return True iff a win has resulted from the last move on the same diagonal, bottom left to top right or top left to bottom right
     */
    default boolean checkDiagonalWin(int r, int c, char p) {
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
        //
        //counts how many X's in a row starting from start X and y
        col = startX;                                                          //col given initial value of Search start column
        for (int y = startY; (y < getNumRows() && col < getNumColumns()); y++) {                    //Increments row and column value until either goes past set board size

            if (whatsAtPos(y, col) == p)                           //Increments count when a valid space is found
                NWtoSEcount++;
            else if (NWtoSEcount < getNumToWin() && (whatsAtPos(y, col) != p))       //Resets count to 0 if invalid space is found
                NWtoSEcount = 0;
            col++;                                                             //Increases column value each pass through
        }

        if (NWtoSEcount >= getNumToWin()) {     //Sets boolean to true if >= positions to win was found
            NWtoSE = true;
        }

        //
        //top right to bottom left
        startX = xMove;          //Resets starting position to column value given by BoardPosition
        startY = yMove;          //Resets starting position to row value given by BoardPosition
        sum = xMove + yMove;     //Adds row and column value to assist in finding search start position

        //Sets Start X and Y positions

        if (sum > getNumColumns() - 1) {               //If sum is greater than 7, we must increment column and decrement row until column == 7;
            while (startX != getNumColumns() - 1) {
                startX++;
                startY--;
            }

        } else {                     //When sum <= 7 search start column = sum, row = 0

            startX = sum;
            startY = 0;
        }
        //
        //counts how many X's in a row starting from start X and y. Must decrement cols and increment rows 1 by 1
        row = startY;                                                       //col given initial value of Search start column
        for (int x = startX; (x >= 0 && row < getNumRows()); x--) {                   //Increments row and decrements column value until either goes past set board size

            if (whatsAtPos(row, x) == p)                        //Increments count when a valid space is found
                NEtoSWcount++;
            else if (NEtoSWcount < getNumToWin() && (whatsAtPos(row, x) != p))    //Resets count to 0 if invalid space is found
                NEtoSWcount = 0;
            row++;                                                          //Increases row value each pass through
        }

        if (NEtoSWcount >= getNumToWin())            //Sets boolean to true if 5 in a row was found
            NEtoSW = true;

        return (NEtoSW || NWtoSE); //returns true if both or either booleans are true and false otherwise


    }
    /**
     * Returns Board in string representation for the user
     * @Precondition Columns < MAX_COLS
     * @Postcondition Board is returned with column numbers at top
     * @return board in string representation
     */
    String toString();

    /**
     * Checks game for a tie
     * @Precondition no moves have resulted in a win
     * @Postcondition return true if the board is full
     * @return true iff all spots have been taken
     */
    boolean checkTie();

    /**
     * Returns the number of rows in the current board
     * @Precondition none
     * @Postcondition Returns the number of rows in current board
     * @return Number of rows
     */
    int getNumRows();

    /**
     * Returns the number of columns in the current board
     * @Precondition none
     * @Postcondition Returns the number of columns in current board
     * @return Number of columns
     */
    int getNumColumns();

    /**
     * Returns the number of positions needed in a row to win in the current board
     * @Precondition none
     * @Postcondition Returns the number of positions needed in a row to win in current board
     * @return Number of positions needed in a row to win
     */
    int getNumToWin();
}
