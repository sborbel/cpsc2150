/**
 * Name: Sean Borbely
 * Class: CPSC2150 - Lab 5
 * Date: 2/27/19
 * Invariant: sizeOfSet = [size of the current set] and [value in set] != [another value in set]
 * Correspondence: this = set[0...MAX_SIZE]
 * Correspondence: size_of_set = sizeOfSet
 */
public class ArraySet<T> extends SetAbs<T> {


    private T[] set;
    private int sizeOfSet;

    /**
     * Constructor for Array set. Creates a new array of size MAX_SIZE
     * and sets the size = 0.
     */
    public ArraySet() {
        set = (T[])new Object[MAX_SIZE];
        sizeOfSet = 0;
    }


    public void add(T val) {
        set[sizeOfSet] = val;
        sizeOfSet++;
    }

    /*
        Creates another array and copies each value besides the value at position
        pos into the array, decrementing the position for each value after
        position pos
     */
    public T removePos(int pos) {
        T[] newArray = (T[])new Object[MAX_SIZE];
        T result;

        for (int i = 0; i < pos; i++) {
            newArray[i] = set[i];
        }

        result = set[pos];

        for (int i = pos + 1; i < MAX_SIZE; i++) {
            newArray[i - 1] = set[i];
        }

        for (int i = 0; i < MAX_SIZE; i++) {
            set[i] = newArray[i];
        }

        sizeOfSet--;
        return result;
    }

    public boolean contains(T val) {
        boolean isThere = false;
        for (int i = 0; i < sizeOfSet; i++) {
            if(set[i].equals(val)){
                isThere = true;
            }
        }
        return isThere;
    }

    public int getSize() {
        return sizeOfSet;
    }





}
