import java.util.*;

// Class to solve the Knight's Tour problem
class Knight_Tour {
    int N; // The dimension of the chessboard (N x N)

    // Default constructor
    Knight_Tour() {}

    // Constructor to initialize the chessboard size
    Knight_Tour(int N) {
        this.N = N;
    }

    // Main method to find the solution to the Knight's Tour problem
    public void Solution_Knight_Tour(int x, int y) {
        int Chess[][] = new int[N][N]; // Create a 2D array to represent the chessboard
        
        // Initialize the chessboard with -1 to mark all cells as unvisited
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Chess[i][j] = -1;
            }
        }

        // Define possible moves for a knight in terms of x and y coordinates
        int MovX[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int MovY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // Mark the starting position on the chessboard with the first move
        Chess[x][y] = 0;

        // Start the recursive process to find the knight's tour
        if (!Tour(x, y, 1, Chess, MovX, MovY)) {
            System.out.println("Solution does not exist for starting position.");
            return; // No solution found
        } else {
            System.out.println("Solution does exist for starting position.");
            PrintChessBoard(Chess); // Print the solution chessboard
        }
    }

    /**
     * Recursive method to solve the Knight's Tour problem
     * @param x Current x-coordinate of the knight
     * @param y Current y-coordinate of the knight
     * @param movei Current move number
     * @param Chess 2D array representing the chessboard
     * @param MovX Array of possible x-direction moves
     * @param MovY Array of possible y-direction moves
     * @return true if a solution is found, false otherwise
     */
    public boolean Tour(int x, int y, int movei, int Chess[][], int MovX[], int MovY[]) {
        int k, x_new, y_new;

        // Base case: if all cells are visited, return true
        if (movei == N * N)
            return true;

        // Try all possible moves for the knight
        for (k = 0; k < 8; k++) {
            x_new = x + MovX[k]; // Calculate the new x-coordinate
            y_new = y + MovY[k]; // Calculate the new y-coordinate

            // Check if the new position is safe
            if (Safe(x_new, y_new, Chess)) {
                Chess[x_new][y_new] = movei; // Mark the move on the chessboard
                
                // Recursively attempt to solve the rest of the tour
                if (Tour(x_new, y_new, movei + 1, Chess, MovX, MovY))
                    return true;
                else {
                    // Backtracking: unmark the cell if the move doesn't lead to a solution
                    Chess[x_new][y_new] = -1;
                }
            }
        }

        return false; // Return false if no valid moves are found
    }

    /**
     * Helper method to check if a cell is safe to visit
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @param Chess 2D array representing the chessboard
     * @return true if the cell is within bounds and unvisited, false otherwise
     */
    public boolean Safe(int x, int y, int Chess[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && Chess[x][y] == -1);
    }

    /**
     * Utility method to print the chessboard with the knight's tour
     * @param Chess 2D array representing the chessboard
     */
    public void PrintChessBoard(int Chess[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(Chess[x][y] + " ");
            }
            System.out.println();
        }
    }
}

public class ass5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get the chessboard dimension from the user
        System.out.print("Enter number of one dimension of chess: ");
        int n = sc.nextInt();

        // Get the starting position of the knight
        System.out.print("Enter starting X position in Chess board: ");
        int x = sc.nextInt();
        System.out.print("Enter starting Y position in Chess board: ");
        int y = sc.nextInt();

        // Create an instance of Knight_Tour and solve the problem
        Knight_Tour K = new Knight_Tour(n);
        K.Solution_Knight_Tour(x, y);
    }
}
