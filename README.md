I made this a few hours, it is not optimalized
This program creates a two-dimensional array named "map" that represents the sudoku board, and then populates that array with 81 instances of the Square class
Each of these Square objects has their own coordinates and number value
The map array is ordered by rows, as all natural two-dimensional arrays are, the columns array is populated with the map array contents in order of columns, and the groups array is populated with the map array contents in order of 3x3 groups
Now with three arrays initialized as ways to reference the sudoku board by row, column, or group, the Square objects that the board is populated with are assigned number values
Each Square object has a possibles ArrayList of Integers with the contents {1, 2, 3, 4, 5, 6, 7, 8, 9}
The purpose of this possibles ArrayList is to represent the only number values that the Square object is capable of being assigned according to Sudoku rules
To apply the Sudoku rules, when a Square object is assigned a number value, two things happen:
 - It is assigned a random number value directly from its own possibles ArrayList
 - The removePossibles() method from the main method is called

The removePossibles() method uses the getRelated() method to get the three arrays of objects that are related to the passed Square object: Its corresponding row, column, and group
With the Square objects from each of the related groups, the removePossibles() method calls the remove() method for each Square object in the arrays and passes the newly assigned number value of the original Square object
The remove() method removes the passed number value from the objects possibles ArrayList
In summary, when a Square object is assigned a number value, the removePossibles() method makes sure that none of the other Square objects in the corresponding row, column, or group can be assigned that number, as per Sudoku rules
The prioritize() method traverses the entire Sudoku board and returns the Square object with the least amount of possible numbers left (the lowest possibles.size() value)
The checkSingles() method transverses the entire Sudoku board and assigns a number value to any Square object that only has one possible number left
These two methods are used to avoid any Square object from running out of possible numbers (which still happens but I brute-forced the solution to this)
With all of these rules set in place, the board can still be generated incorrectly, and idk how to fix that but I can at least work around it
The main method populates the map with Square objects, and then calls the populateSquares() method, which populates each Square object using all of the methods listed thus far
The populateSquares() method runs with a while loop that checks for the condition !boardComplete, a boolean value that only returns true when the board is complete
In the event that any Square object runs out of possible number values (the instance returns true for possibles.isEmpty()), the board is reset and the process continues running
Once the board is complete, the board is printed to the console and the amount of times the board was reset is also printed below that
