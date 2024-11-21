import java.util.*;

public class ass4 {

    /**
     * Helper function to find the vertex with the minimum cost that hasn't been visited yet.
     * @param cost Array holding the cost to reach each vertex from the source vertex.
     * @param vis Array indicating whether a vertex has been visited.
     * @return Index of the vertex with the smallest cost that hasn't been visited.
     */
    static int mincost(int cost[], boolean vis[]) {
        int min = Integer.MAX_VALUE; // Initialize minimum cost as maximum possible value.
        int idx = -1; // Index of the vertex with the smallest cost.

        // Iterate through all vertices to find the unvisited vertex with the minimum cost.
        for (int i = 0; i < cost.length; i++) {
            if (!vis[i] && cost[i] <= min) {
                min = cost[i];
                idx = i;
            }
        }
        return idx; // Return the index of the vertex with the minimum cost.
    }

    /**
     * Function to calculate the shortest path from the source vertex to all other vertices.
     * Implements Dijkstra's algorithm.
     * @param graph Adjacency matrix representation of the graph.
     * @param src Source vertex from which shortest paths are calculated.
     * @param n Number of vertices in the graph.
     */
    static void shortestpath(int graph[][], int src, int n) {
        int cost[] = new int[n]; // Array to hold the minimum cost to reach each vertex.
        boolean vis[] = new boolean[n]; // Array to mark visited vertices.

        // Initialize all costs to maximum and mark all vertices as unvisited.
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.MAX_VALUE;
            vis[i] = false;
        }

        // Cost to reach the source vertex is 0.
        cost[src] = 0;

        // Loop through all vertices to calculate shortest paths.
        for (int i = 0; i < n; i++) {
            // Get the vertex with the minimum cost that hasn't been visited.
            int x = mincost(cost, vis);

            // Mark the vertex as visited.
            vis[x] = true;

            // Update the cost of neighboring vertices of the current vertex.
            for (int j = 0; j < n; j++) {
                // Update cost[j] if:
                // 1. The vertex hasn't been visited.
                // 2. There is an edge between the current vertex (x) and vertex (j).
                // 3. The current cost to reach x is not infinite.
                // 4. The cost to reach j through x is less than the current cost to j.
                if (!vis[j] && graph[x][j] != 0 && cost[x] != Integer.MAX_VALUE && cost[x] + graph[x][j] < cost[j]) {
                    cost[j] = cost[x] + graph[x][j];
                }
            }
        }

        // Print the shortest path costs from the source vertex to all other vertices.
        for (int i = 0; i < cost.length; i++) {
            System.out.println("Vertex " + i + " -> Cost: " + cost[i]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input the number of vertices in the graph.
        System.out.println("Enter the number of vertices:");
        int v = sc.nextInt();

        // Input the source vertex for the shortest path calculations.
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();

        // Create a 2D adjacency matrix to represent the graph.
        int graph[][] = new int[v][v];

        // Input the edges of the graph.
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                System.out.println("Enter edge[" + i + "][" + j + "]:");
                graph[i][j] = sc.nextInt();
            }
        }

        // Print the shortest paths from the source vertex.
        System.out.println("Shortest Paths from Vertex " + src + " are:");
        shortestpath(graph, src, v);
    }
}
/*Enter no of vertex
6
Enter edge[0][0]
0
Enter edge[0][1]
2
Enter edge[0][2]
4
Enter edge[0][3]
0
Enter edge[0][4]
0
Enter edge[0][5]
0
Enter edge[1][0]
2
Enter edge[1][1]
0
Enter edge[1][2]
1
Enter edge[1][3]
7
Enter edge[1][4]
0
Enter edge[1][5]
0
Enter edge[2][0]
4
Enter edge[2][1]
1
Enter edge[2][2]
0
Enter edge[2][3]
0
Enter edge[2][4]
3
Enter edge[2][5]
0
Enter edge[3][0]
0
Enter edge[3][1]
7
Enter edge[3][2]
0
Enter edge[3][3]
0
Enter edge[3][4]
2
Enter edge[3][5]
1
Enter edge[4][0]
0
Enter edge[4][1]
0
Enter edge[4][2]
3
Enter edge[4][3]
2
Enter edge[4][4]
0
Enter edge[4][5]
5
Enter edge[5][0]
0
Enter edge[5][1]
0
Enter edge[5][2]
0
Enter edge[5][3]
1
Enter edge[5][4]
5
Enter edge[5][5]
0
Shortest Path is
0 0
1 2
2 3
3 8
4 6
5 9 */