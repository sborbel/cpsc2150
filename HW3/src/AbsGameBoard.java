/**
 * AbsGameBoard provides a toString method to show the board to the players
 */
public abstract class AbsGameBoard implements IGameBoard{

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
