
/**
 * Name: Sean Borbely
 * Class: CPSC2150 - HW3
 * Date: 3/15/19
 * AbsGameBoard provides a toString() for all implementations of IGameBoard
 */
public abstract class AbsGameBoard implements IGameBoard{

    /**
     * @Precondition board is initialized
     * @Postcondition returns a formatted string representation of the game board
     * @return board in string representation
     */
    @Override
    public String toString(){
        String boardOutline = "| "; //Column numbers

        for(int c = 0; c < getNumColumns(); c++){
            if(c <= 8)
                boardOutline += c + "| ";
            else
                boardOutline += c + "|";
        }

        boardOutline += "\n";
        for (int i = 0; i < getNumRows(); i++) {

            boardOutline += "|";
            for (int j = 0; j < getNumColumns(); j++) {
                boardOutline += whatsAtPos(i, j) + " |";
            }
            boardOutline += "\n";
        }



        return boardOutline;
    }
}
