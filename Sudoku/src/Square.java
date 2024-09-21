import java.util.*;
public class Square {
    //This ArrayList holds all the possible numbers that an instance of this class can be assigned in accordance with Sudoku rules
    public ArrayList<Integer> possibles = new ArrayList<>(10);
    public int x;
    public int y;
    public int num = 0;
    public Square(int y, int x) {
        //Populate the possibles ArrayList with {1,2,3,4,5,6,7,8,9}
        for(int i = 1; i < 10; i++) {
            possibles.add(i);
        }
        this.x = x;
        this.y = y;
    }
    public void checkSingle() {
        //If there is only one possible number left to be assigned, assign that number to this object
        if (possibles.size() == 1 && num == 0) {
            this.populate();
            Main.removePossibles(this);
        }
    }
    public void populate() {
        //Assign a random number from the possibles ArrayList to this object, if there are any numbers left in the possibles ArrayList
        int rand = (int)(Math.floor(Math.random() * possibles.size()));
        if(!possibles.isEmpty()) {
            this.num = possibles.get(rand);
        } else {
            //If the possibles ArrayList is empty, reset the board
            Main.reset();
        }
    }
    public void remove(int removed) {
        //Remove the passed number from the possibles ArrayList
        possibles.remove(Integer.valueOf(removed));
    }
}
