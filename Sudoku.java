/**
 *A sudoku class that holds a sudoku like a 2D array. 0 use as placeholder for
 * unknown value.
 * @author Pak Ho
 */

public class Sudoku {
    
    private int[][] sudoku; //Private value that holds the Sudoku
    
    /*
    Construct a Sudoku with all 0 values
    */
    public Sudoku(){
        
        sudoku = new int[9][9];
        
        for(int row = 0; row < 9; row++){
            
            for(int col = 0; col < 9; col++){
            
                sudoku[row][col] = 0;
            }
        }
    }
    
    /*
    Constructor for the Sudoku class. This one takes in a 2D Array argument but
    if the dimension are wrong, an exception will be thrown.
    */
    public Sudoku(int[][] sudoku) throws Exception {
        
        if(sudoku.length == 9 && sudoku[0].length == 9){
            this.sudoku = sudoku;
        } else {
            
            System.out.println("The dimension of the sudoku passed in is not valid.\n");
            throw new Exception("The dimension of the sudoku inputted is not valid.\n");
        }
    }
    
    /*
    A getter for the current Sudoku in 2D Array format
    @return The 2D Array Sudoku
    */
    public int[][] getSudoku(){
        
        return sudoku;
    }
    
    /*
    A getter for a value in the Sudoku
    @return An integer value in the Sudoku
    */
    public int getVal(int row, int col){
        
        return sudoku[row][col];
    }
    
    /*
    Allows user to set to a new Sudoku. However if the dimensions are wrong, an exception will be thrown.
    */
    public void setSudoku(int[][] sudoku) throws Exception {
        
        if(sudoku.length == 9 && sudoku[0].length == 9){
            
            this.sudoku = sudoku;
        } else {
            
            System.out.println("The dimension of the sudoku passed in is not valid.\n");
            throw new Exception("The dimension of the sudoku inputted is not valid.\n");
        }
    }
    
    /*
    Allows user to set specific values in the Sudoku
    */
    public void setVal(int row, int col, int val){
        sudoku[row][val] = val;
    }
    
    /*
    Check if the current Sudoku obeys the rules of Sudoku
    @return Whether the Sudoku is a valid Sudoku
    */
    public boolean isValidSudoku(){
        
        return isValidValueSudokuPuzzle() && isLegalSudoku();
    }
    
    /*
    Check if the Sudoku has valid values in it.
    @return Whether the sudoku only has valid value in it
    */
    public boolean isValidValueSudokuPuzzle(){
        
        for(int row = 0; row < 9; row++){
            
            for(int col = 0; col < 9; col++){
                
                if(sudoku[row][col] > 9 || sudoku[row][col] < 0){
                    
                    System.out.println("Value in row " + (row+1) + " column " + (col+1 + " has an invalid value in sudoku."));
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /*
    Check if the current Sudoku is a valid Sudoku. 0's can be used to subsitute unknown values
    @return Whether the Sudoku obeys the rules of Sudoku
    */
    public boolean isLegalSudoku(){
        
        boolean result = true;
        
        for(int i = 0; i < 9 && result; i++){ //Double nested loop to go through all position in the sudoku
         
            for(int n = 0; n < 9 && result; n++){
                
                if(sudoku[i][n] != 0){
                
                    if((checkRowSame(i, n) && checkColumnSame(i, n) && checkSquareGridSame(i, n))){ //Check if the values at this position violated any of the rules

                        //System.out.println("Violated one of the sudoku rules.");
                        result = false;
                    }
                }
            }
        }
        return result;
    }
    
    /*
    Check whether there are values in the same row that are the same against the specific position
    @param row The row which the current position is on
    @param column The column which the current position is on
    @return Whether there are values in the same row that are the same
    */
    public boolean checkRowSame(int row, int col){
        
        int val = sudoku[row][col]; //Grab the values in current position
        
        for(int i = 0; i < 9; i++){ //Traverse through the entire row
            
            if(sudoku[row][i] == val && i != col){ //Check if there's a value in the row that is current position
                
                //System.out.println("Value in column " + (col+1) + " and in column " + (i+1) + " in row " + (row+1) + " is the same."); //Error message if there's a problem
                return true;
            }
        }
        
        return false;
    }
    
    /*
    Check whether there are values in the same column that are the same against the specific position
    @param row The row which the current position is on
    @param column The column which the current position is on
    @return Whether there are values in the same row that are the same
    */
    public boolean checkColumnSame(int row, int col){
        
        int val = sudoku[row][col]; //Grab the values in current position
        
        for(int i = 0; i < 9; i++){ //Traverse through the entire column
            
            if(sudoku[i][col] == val && i != row){ //Check if there's a value in the column that is current position
                
                //System.out.println("Value in row " + (row+1) + " and in row " + (i+1) + " in column " + (col+1) + " is the same."); //Error message if there's a problem
                return true;
            }
        }
        
        return false;
    }
    
    /*
    Check whether there are values in the same 3x3 grid that are the same against the specific position
    @param row The row which the current position is on
    @param column The column which the current position is on
    @return Whether there are values in the same 3x3 grid that are the same
    */
    public boolean checkSquareGridSame(int row, int col){
        
        int val = sudoku[row][col]; //Grab the values in current position
        int gridRow = row/3; //Find which grid row it is on
        int gridCol = col/3; //Find which grid column this is on
        
        for(int i = 3*gridRow; i < 3*gridRow + 3; i++){ //Traverse through this 3x3 grid's row
            
            for(int n = 3*gridCol; n < 3*gridCol + 3; n++){ //Traverse through this 3x3 grid's column
                
                if(val == sudoku[row][col] && (i!= row || n != col)){ //Check if there are values that are the same in this grid
                    
                    //System.out.println("Something in row " + (row+1) + " column " + (col+1) + " is the same as row " + (i+1) + " column " + (n+1) + "."); //Error message if there's a problem
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /*
    A method to print the current form of the sudoku
    */
    public void printSudoku(){
        
        System.out.println("Currently the Sudoku is: ");
        
        for(int i = 0; i < 9; i++){ //Double nested loop to go through all 81 position in the sudoku
            
            System.out.print("| "); //Add | at the beginning of every row
            
            for(int n = 0; n < 9; n++){
                
                System.out.print(sudoku[i][n] + " ");
                
                if(n%3 == 2){
                    System.out.print("| "); //Add | after every 3 numbers to help separate the printed form into tinier grids
                }
            }
            
            System.out.println();
        }
    }
}
