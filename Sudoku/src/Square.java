import java.util.*;
public class Square {
    public ArrayList<Integer> possibles = new ArrayList<>(10);
    public int x;
    public int y;
    public int num = 0;
    public Square(int y, int x) {
        for(int i = 1; i < 10; i++) {
            possibles.add(i);
        }
        this.x = x;
        this.y = y;
    }
    public void checkSingle() {
        if (possibles.size() == 1 && num == 0) {
            this.populate();
            Main.removePossibles(this);
        }
    }
    public void populate() {
        int rand = (int)(Math.floor(Math.random() * possibles.size()));
        if(!possibles.isEmpty()) {
            this.num = possibles.get(rand);
        } else {
            Main.reset();
        }
    }
    public void remove(int removed) {
        possibles.remove(Integer.valueOf(removed));
    }
}
