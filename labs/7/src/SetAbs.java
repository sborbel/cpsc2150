import java.lang.*;


public abstract class SetAbs<T> implements ISet<T>{

    public abstract void add(T val);

    public abstract T removePos (int pos);

    public abstract boolean contains(T val);

    public abstract int getSize();


    /**
     * Description: Returns current set as a String
     * Precondition: NULL
     * Post-condition: val holds set in string notation
     * return: set in String notation
     */
    public String toString(){
        String res = "";
        T temp;
        ISet<T> t = this;
        for(int i = 0; i < t.getSize(); i++){
            temp = t.removePos(0);
            res += temp.toString() + ", ";
            t.add(temp);
        }
        return res;
    }

    /**
     * Description: Checks if two sets contain exactly the same values
     * @Precondition: true
     * @Post-condition: Checks to see if two sets are equal
     * @param ob Second set
     * @return true iff the two sets are exactly equal
     */
    public boolean equals(Object ob){
        boolean equal = false;
        T placeHolder;
        SetAbs<T> obCopy;
        SetAbs<T> thisCopy;
        if(ob instanceof ISet){
            if(ob instanceof ArraySet){
                obCopy = new ArraySet<>();
            }
            else {
                obCopy = new ListSet<>();
            }

            if(this instanceof ArraySet){
                thisCopy = new ArraySet<>();
            }
            else {
                thisCopy = new ListSet<>();
            }

            SetAbs<T> temp = (SetAbs<T>)ob;

            int obSize = temp.getSize();
            int thisSize = this.getSize();

            for(int i = 0; i < obSize; i++){
                placeHolder = temp.removePos(0);
                obCopy.add(placeHolder);
                temp.add(placeHolder);
            }

            for(int i = 0; i < thisSize; i++){
                placeHolder = this.removePos(0);
                thisCopy.add(placeHolder);
                this.add(placeHolder);
            }
            thisCopy.difference(obCopy);
            equal = thisCopy.getSize() == 0;

        }


        return (equal);

    }
}
