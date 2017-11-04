/**
 * Simple Sudoku program to solve a Sudoku puzzle
 * @author Rishab Narendra
 */

/**
 * Sample Sudoku puzzle for user understanding
 * 
  4 0 0 |0 0 0 |8 0 5  =   4 1 7 |3 6 9 |8 2 5 
  0 3 0 |0 0 0 |0 0 0  =   6 3 2 |1 5 8 |9 4 7
  0 0 0 |7 0 0 |0 0 0  =   9 5 8 |7 2 4 |3 1 6 

  0 2 0 |0 0 0 |0 6 0  =   8 2 5 |4 3 7 |1 6 9 
  0 0 0 |0 8 0 |4 0 0  =   7 9 1 |5 8 6 |4 3 2 
  0 0 0 |0 1 0 |0 0 0  =   3 4 6 |9 1 2 |7 5 8 

  0 0 0 |6 0 3 |0 7 0  =   2 8 9 |6 4 3 |5 7 1 
  5 0 0 |2 0 0 |0 0 0  =   5 7 3 |2 9 1 |6 8 4 
  1 0 4 |0 0 0 |0 0 0  =   1 6 4 |8 7 5 |2 9 3 
*/
	

public class Sudoku {
	
	/**
	 * Instance variable that holds the current sudoku puzzle
	 */
	private static int[][] grid =  
	{{4, 0, 0, 0, 0, 0, 8, 0, 5}, {0, 3, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 0}, {0, 2, 0, 0, 0, 0, 0, 6, 0},{0, 0, 0, 0, 8, 0, 4, 0, 0},{0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 6, 0, 3, 0, 7, 0}, {5, 0, 0, 2, 0, 0, 0, 0, 0},{1, 0, 4, 0, 0, 0, 0, 0, 0} };
	
	public static class gridReference
	{
		/**
		 * Instance variable that stores the index of the row
		 */
		private int row;
		
		/**
		 * Instance value that stores the index of the column
		 */
		private int column;
		
		/**
		 * Constructor to initialize the cell value we are currently looking at 
		 * @param row
		 * 		row index
		 * @param col
		 * 		column index
		 */
		public gridReference(int row, int col) 
		{ 
			this.row = row;
			this.column = col;
		}
	}
	
	/**
	 * Method to check if the value stored in the grid[][] position is valid. There are 3 cases for which it is not valid: 
	 * 1) If the value is already present in the row then it cannot exist again in the same row
	 * 2) If the value is already present in the column then it cannot exist again in the same column
	 * 3) If the value exists in each of the mini 3*3 matrices, then it cannot exist again in the same mini matrix
	 * If the above three conditions do not hold true then the value is valid 
	 * @param cell
	 * 		the location of the row and column of the current cell
	 * @param val
	 * 		the value we are trying to add
	 * @return
	 * 		true/false depending upon whether the value is valid or not based upon the above mentioned conditions
	 */
	public static boolean isValid(gridReference cell, int val) 
	{
		if(grid[cell.row][cell.column] != 0)
			throw new RuntimeException("This cell has already been initialized!!!");
		
		// if value is already present in the row then grid[][] cannot store that value according to Sudoku rules
		for (int c = 0; c < 9; c++) 
		{
			if (grid[cell.row][c] == val)
		    return false;
		}

		// if value is already present in the column then grid[][] cannot store that value according to Sudoku rules
		for (int r = 0; r < 9; r++)
		{
			if (grid[r][cell.column] == val)
				return false;
		}

		// checks for each 3*3 box and makes sure that the grid[][] does not already contain that value
		int x1 = 3 * (cell.row / 3);
		int y1 = 3 * (cell.column / 3);

		for (int x = x1; x <= (x1 + 2); x++)
			for (int y = y1; y <= (y1 + 2); y++)
				if (grid[x][y] == val)
					return false;

		return true;
	}
	
	/**
	 * 
	 * @param current
	 * @return
	 */
	public static boolean solve(gridReference current) 
	{			
		// if current is null this means that the puzzle is complete
		if (current == null)
			return true;

		// if grid[][] is not equal to 0 that means it is already solved, we need to move on to the next cell 
		if (grid[current.row][current.column] != 0) {
			return solve(next(current));
		}
  
		for (int i = 1; i <= 9; i++) 
		{
			if (!isValid(current, i)) 
				continue;

			grid[current.row][current.column] = i;

			if (solve(next(current))) 
				return true;
			else
				grid[current.row][current.column] = 0;
		}
		
		// unsolvable
		return false;
	}
	
	/**
	 * gridReference returns the location of the row and column for the next element
	 * @param current
	 * 		current is the location of the row and column of the current element 
	 * @return
	 * 		returns the gridReference, that is, the location of the row and column of the next element  
	 */
	 public static gridReference next(gridReference current) 
	 {
		 int row = current.row;
		 int col = current.column;

		 // next cell is in the same row but next column
		 col++;

		 // if col>8 then we proceed to the next column and next row
		 if (col > 8) 
		 {
			 col = 0;
			 row++;
		 }

		 // if row>8 then we are at the end of grid[][]
		 if (row > 8)
			 return null; 

		 return new gridReference(row, col);
	 }

	/**
	 * Simply print method that displays the sudoku puzzle 
	 * @param grid
	 * 		grid is the matrix representation of the sudoku puzzle
	 */
	 public static void print(int grid[][]) 
	 {
		  for (int i = 0; i< 9; i++) 
		  {
			  for (int j = 0; j < 9; j++)
			  {
				  System.out.print(grid[i][j]);
				  System.out.print(" ");
				  if(j == 2 | j == 5)
					  System.out.print("|");
			  }
			  if(i == 2 || i == 5)
				  System.out.println();
			  System.out.println();
		  }
	  }
	
	 /**
	  * main method
	  * program starts here
	  * @param args
	  */
	public static void main(String[] args) 
	{
		System.out.println("This is the puzzle we want to solve based on your input.....");
		print(grid);
		
		boolean solved = solve(new gridReference(0, 0));
		  
		if (!solved) 
		{
			System.out.println("You have either passed a wrong input or an unsolvable sudoku puzzle!! Please modify your grid!!");
			return;
		}
		  
		 System.out.println();
		 System.out.println();
		 print(grid);
	}
}
