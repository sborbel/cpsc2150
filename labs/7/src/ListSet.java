import java.util.*;
/**
 * Name: Sean Borbely
 * Class: CPSC2150 - Lab 4
 * Date: 2/14/19
 * Invariant [value in set] != [another value in set]
 * Correspondence: this = myList
 * Correspondence: size_of_set = this.size()
 */
public class ListSet<T> extends SetAbs<T> {


    private List<T> myList;

    public ListSet(){
        myList = new LinkedList<>();
    }

    @Override
    public void add(T val) {
        myList.add(val);
    }

    public T removePos(int pos) {
        T myVal;
        myVal = myList.get(pos);
        myList.remove(pos);
        return myVal;
    }


    public boolean contains(T val) {

        return myList.contains(val);
    }

    public int getSize() {
        return myList.size();
    }

    /**
     * Description: Returns current set as a String
     * Precondition: NULL
     * Post-condition: val holds set in string notation
     * return: set in String notation
     */
    /*
    @Override
    public String toString(){
        String val = "";
        for(int i = 0; i < getSize(); i++){
            val += myList.get(i).toString();
            val += ", ";
        }
        return val;
    }
    */
}
