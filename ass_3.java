import java.util.Scanner;
public class ass_3 {
    /**
     * Function to implement the Floyd-Warshall algorithm.
     * This algorithm finds the shortest paths between all pairs of vertices in a graph.
     * It works by iteratively improving the shortest paths using an intermediate vertex.
     * 
     * @param mat The adjacency matrix of the graph, where mat[i][j] represents
     *            the weight of the edge from vertex i to vertex j.
     *            A value of Integer.MAX_VALUE represents no direct edge (infinite distance).
     */
    public static void warshall(int mat[][]) {
        int n = mat.length; // Number of vertices in the graph.

        // Iteratively consider each vertex as an intermediate vertex.
        for (int k = 0; k < n; k++) {
            // Loop through all pairs of vertices (i, j).
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // If the vertex k is on the path from i to j and both paths i->k and k->j
                    // exist, update the shortest distance from i to j.
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }

        // Print the resulting shortest distance matrix.
        printMatrix(mat);
    }
    /**
     * Helper function to print a matrix.
     * Converts Integer.MAX_VALUE to "INF" for better readability of infinite distances.
     * 
     * @param mat The matrix to print.
     */
    public static void printMatrix(int mat[][]) {
        int n = mat.length; // Number of rows (and columns) in the matrix.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // If the distance is Integer.MAX_VALUE, print "INF" (representing infinity).
                if (mat[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(mat[i][j] + " "); // Print the finite distance.
                }
            }
            System.out.println(); // Move to the next row.
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of vertices in the graph.
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();

        // Create an adjacency matrix for the graph.
        int mat[][] = new int[n][n];

        System.out.println("Enter -1 for infinite distances:");

        // Input the adjacency matrix.
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the distances from vertex " + (i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                int input = sc.nextInt();
                if (input >= 0) {
                    mat[i][j] = input; // Set the distance if the input is non-negative.
                } else {
                    mat[i][j] = Integer.MAX_VALUE; // Use Integer.MAX_VALUE for infinite distances.
                }
            }
        }

        // Print the input adjacency matrix.
        System.out.println("The input matrix is:");
        printMatrix(mat);

        // Apply the Floyd-Warshall algorithm to find shortest paths.
        System.out.println("The matrix after applying Floyd Warshall Algorithm:");
        warshall(mat);

        // Close the scanner to release resources.
        sc.close();
    }
}
/*
Output:
Enter the number of vertices: 4
Enter -1 for infinite distances:
Enter the distances from vertex 1: 0 3 -1 7
Enter the distances from vertex 2: 8 0 2 -1
Enter the distances from vertex 3: 5 -1 0 1
Enter the distances from vertex 4: 2 -1 -1 0
The input matrix is:
0 3 INF 7
8 0 2 INF
5 INF 0 1
2 INF INF 0
The matrix after applying Floyd Warshall Algorithm:
0 3 5 6
5 0 2 3
3 6 0 1
2 5 7 0
 */