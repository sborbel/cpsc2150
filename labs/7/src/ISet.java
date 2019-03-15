import java.util.LinkedList;
import java.util.List;

/**
 * Name: Sean Borbely
 * Class: CPSC2150 - Lab 4
 * Date: 2/14/19
 *
 * Set represents a mathematical set of Ts
 *
 * Defines: size_of_set: z
 *
 * Constraints: 0 <= size_of_set <= MAX_SIZE
 *
 * Initialization ensures:
 *      Set is an empty set
 */
public interface ISet<T>{
    int MAX_SIZE = 100;
    /**
        Description: Adds a value, val, to the set
        Precondition: !(pos ∈ set)
        [pos is the value at position pos in the set]
        Postcondition: pos ∈ set
        [pos is the value at position pos in the set]
        Param: val = value to be added to the set
     */
    void add(T val);

    /**
        Description: Removes an item from the set at position pos
        Precondition: pos < size - 1 && pos > 0 && set != {}
        [size is the number of values in the set]
        Post-condition: #(T @ pos) !∈ set
        [#(T @ pos) is the original T at position pos int the original set]
        Param: pos = position in set to remove
        Returns: T at position pos
     */
    T removePos(int pos);

    /**
        Description: Checks if set contains the T val
        Precondition: true
        Post-condition: returns true iff val is in the set
        Param: val = value to be searched for in list
        Returns: true iff val ∈ set
     */
    boolean contains(T val);

    /**
        Description: Returns the size of the set, as how many items it contains
        Precondition: Sets do not contain duplicate values
        Post-condition: returns the size of the set
        Returns: Size of set
     */


    /**
     * Description: Computes the union of two sets
     * param unionWith set to be compared to this
     * Precondition: sets do not contain any duplicate values
     * Post-condition: this contains the union of this and unionWith
     */
    default void union(ISet<T> unionWith){
        T currVal;
        int size = unionWith.getSize();



        for(int i = 0; i < size; i++){
            currVal = unionWith.removePos(0);
            if(!this.contains(currVal)){
                this.add(currVal);
            }
        }
        }

        int getSize();

    /**
     * Description: Computes the intersection of two sets
     * param intWith set to be compared with this
     * Precondition: sets do not contain duplicate values
     * Post-condition: this contains the intersection between this and intWith
     */
    default void intersect(ISet<T> intWith){
        T currVal;
        int i = 0;
        int j = 0;
        int thisSize = this.getSize();
        int paramSize = intWith.getSize();



        List<T> a = new LinkedList<>();
        while (i < thisSize){
            a.add(this.removePos(0));
            i++;
        }

        List<T> b = new LinkedList<>();
        while(j < paramSize){
            b.add(intWith.removePos(0));
            j++;
        }

        List<T> result = new LinkedList<>();

        for(int k = 0; k < a.size(); k++){
            if(b.contains(a.get(k))){
                result.add(a.get(k));
            }
        }

        for(int k = 0; k < result.size(); k++){
            this.add(result.get(k));
        }
    }

    /**
     * Description: Computes the difference between two sets
     * param intWith set to be compared with this
     * Precondition: true
     * Post-condition: this contains the intersection between this and intWith
     */
    default void difference(ISet<T> intWith){
        int i = 0;
        int j = 0;
        int thisSize = this.getSize();
        int paramSize = intWith.getSize();

        List<T> a = new LinkedList<>();
        while (i < thisSize){
            a.add(this.removePos(0));
            i++;
        }

        List<T> b = new LinkedList<>();
        while(j < paramSize){
            b.add(intWith.removePos(0));
            j++;
        }

        List<T> result = new LinkedList<>();

        for(int k = 0; k < a.size(); k++){
            if(!b.contains(a.get(k))){
                result.add(a.get(k));
            }
        }

        for(int k = 0; k < result.size(); k++){
            this.add(result.get(k));
        }


    }

}

