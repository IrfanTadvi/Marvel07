import java.util.*;

// Class C represents a state in the assignment process.
class C implements Comparable<C> {
    int sl;      // Number of students assigned to clubs so far.
    int[] ac;    // Array storing the club assignments for students (-1 means unassigned).
    int c;       // Current total cost of the assignments made so far.
    int lb;      // Lower bound (minimum estimated cost to complete the assignment).

    // Constructor to initialize the state.
    public C(int sl, int[] ac, int c, int lb) {
        this.sl = sl;          // Number of students assigned so far.
        this.ac = ac.clone();  // Make a copy of the assignment array.
        this.c = c;            // Current total cost.
        this.lb = lb;          // Lower bound.
    }

    // Compare nodes based on the lower bound (lb) to prioritize cheaper assignments.
    @Override
    public int compareTo(C o) {
        return Integer.compare(this.lb, o.lb);
    }
}

public class ass6 {
    // Function to find the minimum cost assignment using Branch and Bound.
    public static int aClubs(int[][] cm, int[] res) {
        int n = cm.length; // Number of students (or clubs, since both are equal).
        
        // Priority Queue to explore states, ordered by the lowest bound (lb).
        PriorityQueue<C> pq = new PriorityQueue<>();
        
        // Initial assignment array (-1 means no clubs assigned yet).
        int[] ia = new int[n];
        Arrays.fill(ia, -1);

        // Create the initial state: no students are assigned, cost is 0, and calculate lb.
        C r = new C(0, ia, 0, lb(cm, ia, 0));
        pq.add(r); // Add the initial state to the priority queue.

        // Process nodes from the queue until an optimal solution is found.
        while (!pq.isEmpty()) {
            C cur = pq.poll(); // Extract the state with the smallest lower bound.

            // If all students are assigned to clubs, the solution is complete.
            if (cur.sl == n) {
                System.arraycopy(cur.ac, 0, res, 0, n); // Copy the assignments to result array.
                return cur.c; // Return the total cost.
            }

            int st = cur.sl; // Get the current student to be assigned.

            // Try assigning the current student (st) to every available club (cl).
            for (int cl = 0; cl < n; cl++) {
                if (!clubAsg(cur.ac, cl)) { // Check if the club is not already assigned.
                    int[] newAc = cur.ac.clone(); // Copy the current assignments.
                    newAc[st] = cl;              // Assign the current student to the club.
                    int newC = cur.c + cm[st][cl]; // Update the total cost.
                    int newLb = lb(cm, newAc, st + 1); // Calculate the new lower bound.

                    // Create a new state with the updated assignments and add it to the queue.
                    C newNode = new C(st + 1, newAc, newC, newLb);
                    pq.add(newNode);
                }
            }
        }

        return -1; // If no solution is found (this should not happen for valid input).
    }

    // Function to check if a club is already assigned to any student.
    private static boolean clubAsg(int[] ac, int cl) {
        for (int a : ac) {
            if (a == cl) return true; // If club is found in the assignment array, return true.
        }
        return false; // Otherwise, return false.
    }

    // Function to calculate the lower bound (minimum estimated cost to complete the assignment).
    private static int lb(int[][] cm, int[] ac, int sl) {
        int n = cm.length; // Number of students (or clubs).
        int l = 0; // Initialize the lower bound.

        // Add the cost of already-assigned students.
        for (int i = 0; i < sl; i++) {
            l += cm[i][ac[i]];
        }

        // For unassigned students, add the minimum possible cost for each.
        for (int i = sl; i < n; i++) {
            int min = Integer.MAX_VALUE; // Start with a large value.
            for (int j = 0; j < n; j++) {
                if (!clubAsg(ac, j)) { // Check only unassigned clubs.
                    min = Math.min(min, cm[i][j]); // Find the minimum cost.
                }
            }
            l += min; // Add the minimum cost for the current student.
        }

        return l; // Return the calculated lower bound.
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of students (equal to the number of clubs).
        System.out.print("Enter number of students/clubs: ");
        int n = sc.nextInt();

        // Input: cost matrix where cm[i][j] is the cost of assigning student i to club j.
        int[][] cm = new int[n][n];
        System.out.println("Enter the cost matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cm[i][j] = sc.nextInt();
            }
        }

        // Array to store the final club assignments for each student.
        int[] res = new int[n];

        // Call the assignment function and get the minimum cost.
        int minC = aClubs(cm, res);

        // Output the results.
        System.out.println("The minimum cost of assigning clubs is: " + minC);
        System.out.println("Club assignments (student -> club): ");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + " -> Club " + (res[i] + 1));
        }
    }
}
/*Enter number of students/clubs: 4
Enter the cost matrix:
4
4
3
2
2
1
2
6
7
5
9
4
3
2
2
5
The minimum cost of assigning clubs is: 11
Club assignments (student -> club):
Student 1 -> Club 3
Student 2 -> Club 1
Student 3 -> Club 4
Student 4 -> Club 2 */