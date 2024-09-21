import java.util.*;
import java.lang.Math.*;
public class Main {
    public static Square[][] map = new Square[9][9];
    public static Square[][] columns = new Square[9][9];
    public static Square[][][] groups = new Square[3][3][9];
    public static boolean boardComplete = false;
    public static int resetCount = 0;
    public static void populateMap() {
        //Populate map (acts as rows as well)
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                map[i][j] = new Square(i, j);
            }
        }
        //Populate columns
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                columns[i][j] = map[j][i];
            }
        }
        //Populate groups
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        groups[i][j][(k*3)+l] = map[(i*3)+k][(j*3)+l];
                    }
                }
            }
        }
    }
    public static void checkSingles() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                map[i][j].checkSingle();
            }
        }
    }
    public static void populateSquares() {
        while(!boardComplete) {
            checkSingles();
            Square priority = priority();
            priority.populate();
            removePossibles(priority);
            isBoardComplete();
        }
        System.out.println();
    }
    public static void reset() {
        resetCount++;
        map = new Square[9][9];
        columns = new Square[9][9];
        groups = new Square[3][3][9];
        boardComplete = false;
        populateMap();
    }
    public static void isBoardComplete() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(map[i][j].num == 0) {
                    return;
                }
            }
        }
        boardComplete = true;
    }
    public static Square priority() {
        int tinySize = 9;
        Square tinySquare = map[0][0];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(map[i][j].possibles.size() < tinySize && map[i][j].num == 0) {
                    tinySquare = map[i][j];
                }
            }
        }
        return tinySquare;
    }
    public static void printMap() {
        System.out.println("  --------------------------------------------------------");
        for(int i = 0; i < 9; i++) {
            System.out.print("  |  ");
            for(int j = 0; j < 9; j++) {
                System.out.print(map[i][j].num + "  |  ");
            }
            System.out.println();
            System.out.println("  --------------------------------------------------------");
        }
        /*
        Print groups
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 9; k++) {
                    System.out.print(groups[i][j][k].num + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }*/
    }
    public static Square[][] getRelated(int x, int y) {
        Square[][] related = new Square[3][9];
        related[0] = map[y];
        related[1] = columns[x];
        int groupX = (int)(Math.ceil((y+1.0)/3)-1);
        int groupY = (int)(Math.ceil((x+1.0)/3)-1);
        related[2] = groups[groupX][groupY];
        return related;
    }
    public static void removeList(Square[] list, int removed) {
        for(int i = 0; i < 9; i++) {
            list[i].remove(removed);
        }
    }
    public static void removePossibles(Square square) {
        int num = square.num;
        Square[][] related = getRelated(square.x, square.y);
        Square[] row = related[0];
        Square[] column = related[1];
        Square[] group = related[2];
        removeList(row, num);
        removeList(column, num);
        removeList(group, num);
    }
    public static void main(String[] args) {
        populateMap();
        populateSquares();
        printMap();
        System.out.println();
        System.out.println("Board was reset " + resetCount + " times.");
        /*ArrayList<Integer> test = new ArrayList<>(9);
        for(int i = 1; i < 10; i++) {
            test.add(i);
        }*/
    }
}