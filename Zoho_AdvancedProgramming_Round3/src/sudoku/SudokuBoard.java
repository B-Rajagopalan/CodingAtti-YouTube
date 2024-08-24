package sudoku;

public class SudokuBoard {
    int[][] board;
    final int INNER_DIMENSION = 3;

    public SudokuBoard(int[][] board) {
        this.board = board;
    }

    public void print() {
        for (int row = 0; row < board.length; row++) {
            // Empty space for 3x3 row
            if (row % INNER_DIMENSION == 0) System.out.println();
            for (int column = 0; column < board.length; column++) {
                // Empty space for 3x3 column
                if (column % INNER_DIMENSION == 0) System.out.print("  ");
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public boolean solve(int row, int column) {
        //Check do we reached bottom of the board
        if (row == board.length) {
            //Move to next column
            column++;
            //At this point we reached bottom right of the board, so we found the solution
            if (column == board.length) return true;
                //reset the row to 0 to fill the next column from the start
            else row = 0;
        }

        //Skip the cell if it has already filled
        if (board[row][column] != 0) return solve(row + 1, column);

        // Fill the cell with all possibilities
        for (int num = 1; num <= board.length; num++) {
            //Check is it a valid cell
            if (isValid(row, column, num)) {
                board[row][column] = num;
                //check if the next sub problem is solved
                if (solve(row + 1, column)) return true;
                //if the above sub problem is not solved BACKTRACK
                board[row][column] = 0;
            }
        }
        return false;
    }

    public boolean isValid(int row, int column, int num) {
        //Does num already in row
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) return false;
        }

        //Does num already in column
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] == num) return false;
        }

        // Check if num is in the 3x3 subgrid
        int startRow = (row / INNER_DIMENSION) * INNER_DIMENSION;
        int startCol = (column / INNER_DIMENSION) * INNER_DIMENSION;

        for (int i = startRow; i < startRow + INNER_DIMENSION; i++) {
            for (int j = startCol; j < startCol + INNER_DIMENSION; j++) {
                if (board[i][j] == num) return false;
            }
        }

        // If num is not found in the row, column, or 3x3 subgrid, it is valid
        return true;
    }
}
