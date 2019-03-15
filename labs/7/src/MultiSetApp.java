
/*
 * Name: Sean Borbely
 * Class: CPSC2150 - Lab 4
 * Date: 2/14/19
 */
import java.util.*;
public class MultiSetApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        SetAbs<Integer> mySet1;
        SetAbs<Integer> mySet2;
        int choice;
        int selection;
        Integer toAdd;
        Integer toRemove;
        String user;
        boolean keepAdding = true;

        while (!exit) {
            keepAdding = true; //Changes keepAdding back to true so we can add values

            //Gets what the user wants to do
            do {
                System.out.println("Make a selection");
                System.out.println("1. Find the Union of Two Sets\n" +
                        "2. Find the intersection of Two Sets\n" +
                        "3. Find the difference of two sets\n" +
                        "4. See if two sets are equal\n" +
                        "5. Exit");
                selection = in.nextInt();
            } while (selection < 1 || selection > 5);
            if (selection != 5) {

                //Set 1

                System.out.println("Make set 1");

                do {
                    System.out.println("Enter 1 for an array implementation or 2 for a list implementation\n");
                    choice = in.nextInt();
                } while (choice != 1 && choice != 2);
                if (choice == 1) {
                    mySet1 = new ArraySet<>();
                } else
                    mySet1 = new ListSet<>();

                do {
                    System.out.println("Enter a value to add to the set. Enter q to stop adding to the set");
                    user = in.next();

                    if (user.equals("q")) {
                        keepAdding = false;
                    }
                    else if(mySet1.contains(Integer.parseInt(user))){
                        System.out.println("Value is already in the set");
                    }
                    else if(!mySet1.contains(Integer.parseInt(user))) {
                        toAdd = Integer.parseInt(user);
                        mySet1.add(toAdd);
                    }
                } while (keepAdding);

                keepAdding = true; //Changes keepAdding back to true so we can add values

                //Set 2

                System.out.println("Make set 2");


                do {
                    System.out.println("Enter 1 for an array implementation or 2 for a list implementation\n");
                    choice = in.nextInt();
                } while (choice != 1 && choice != 2);
                if (choice == 1) {
                    mySet2 = new ArraySet<>();
                } else
                    mySet2 = new ListSet<>();

                //Loops adding values to the set
                do {
                    System.out.println("Enter a value to add to the set. Enter q to stop adding to the set");
                    user = in.next();
                    if (user.equals("q")) {
                        keepAdding = false;
                    }
                    else if(mySet2.contains(Integer.parseInt(user))){
                        System.out.println("Value is already in the set");
                    }
                    else {
                        toAdd = Integer.parseInt(user);
                        mySet2.add(toAdd);
                    }
                } while (keepAdding);

                //Checks user input
                if (selection == 1) {
                    System.out.println("Set 1 is:\n" + mySet1);
                    System.out.println("Set 2 is:\n" + mySet2);
                    mySet1.union(mySet2);
                    System.out.println("The union is:\n" + mySet1);
                } else if (selection == 2) {
                    System.out.println("Set 1 is:\n" + mySet1);
                    System.out.println("Set 2 is:\n" + mySet2);
                    mySet1.intersect(mySet2);
                    System.out.println("The intersection is:\n" + mySet1);
                } else if (selection == 3) {
                    System.out.println("Set 1 is:\n" + mySet1);
                    System.out.println("Set 2 is:\n" + mySet2);
                    mySet1.difference(mySet2);
                    System.out.println("The difference is:\n" + mySet1);
                } else if(selection == 4){
                    boolean isEqual = mySet1.equals(mySet2);
                    System.out.println("Set 1 is:\n" + mySet1);
                    System.out.println("Set 2 is:\n" + mySet2);
                    if(isEqual){
                        System.out.println("Sets are equal");
                    }
                    else{
                        System.out.println("Sets are not equal");
                    }
                }
                else {
                    exit = true;
                }
            }
            else{
                exit = true;
            }
        }






    }


}
