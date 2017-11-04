# Sudoku-Solver
Program that generates a random 9*9 Sudoku grid and solves it. For now my implementation has a grid already defined. I will be updating that in the next few weeks to work for any randomnly generated puzzle. 

**Prerequisites:**
To run this program you will need a Java IDE Compiler. The java file contains a main method so no other tools are required to run the program. 
Some of the sudoku solvers prerequisites include the following:
1) The grid is a 9*9 grid just like any other sudoku puzzle.
2) Each of the cells contains a value from 1-9 inclusive.
3) The puzzle follow the standard norm for sudoku puzzle solving.
4) Cells that do not contain any value and need to be solved are denoted by the number 0. 

**Explanation of some of the algorithm used -** 
1) isValid(gridReference cell, int val): 
This method checks if the number placed into the grid as a solution is valid. Validity depends upon certain conditions. The value(val) is invalid if it meets any of the requirements: 
a) If the value is already present in the row we are currently on(cell.row).
b) If the value is already present in the column we are currently on(cell.column).
c) If the value is already present in any of the 3*3 mini matrices. 
All the above requirements are based out of the original rules for sudoku puzzle solving. 
If the value does not fall into any of the three categories above, it is automatically treated as valid. 

2) solve(gridReference current):
This method checks if the puzzle can be solved. If the current value passed is null, this means that the puzzle is already solved. Moreover, if the value at the current cell position is not zero, we move on to the next cell. Then, using the isValid method we check for all possible values of a cell, that is, 1-9. If we are unable to find a solvable value, this method returns false and the puzzle is unsolvable. 

3) gridReference next(gridReference current): 
This method simply returns the next cell corresponding to the current cell. The next cell is simply in the same row and next column as long as column is less than 8(9*9 grid). If the column value is above 8, it is set to 0 and we increment the row value. Moreover, if the row value is greater than 8 this means that we have gone through the entire grid. 

4) print(int grid[][]): 
This method is simple and displays our input and output, that is, the unsolved puzzle and the solved puzzle. 

Pseudocode derived from Code For Dummies
