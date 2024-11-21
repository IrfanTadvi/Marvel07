import java.util.*;

// Class to represent a Job
class Job {
    int jobID;      // Unique identifier for the job
    int deadline;   // Deadline of the job (maximum time by which it should be completed)
    int profit;     // Profit associated with completing the job

    // Constructor to initialize a job
    Job(int jobID, int deadline, int profit) {
        this.jobID = jobID;
        this.deadline = deadline;
        this.profit = profit;
    }

    // Getter method to retrieve the profit of a job
    int getprofit() {
        return profit;
    }
}

// Main class to implement the Job Scheduling problem
public class ass_2 {

    /**
     * Function to schedule jobs to maximize profit while adhering to deadlines.
     *
     * @param jobs Array of Job objects containing job ID, deadline, and profit.
     * @param n    Number of jobs in the array.
     */
    void final_ans(Job[] jobs, int n) {
        // Find the maximum deadline among all jobs
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        // Create an array of slots representing time slots up to the maximum deadline
        int m = maxDeadline;
        int[] slot = new int[m];
        Arrays.fill(slot, 0); // Initialize all slots as empty (0)

        // Sort jobs in decreasing order of profit
        Arrays.sort(jobs, Comparator.comparingInt(Job::getprofit).reversed());

        // Variables to track the count of scheduled jobs and the total profit
        int cnt = 0, total = 0;

        // Iterate over each job to schedule them in an available time slot
        for (int i = 0; i < n && cnt < m; i++) {
            // Start checking from the latest available time slot for the current job
            for (int j = jobs[i].deadline - 1; j >= 0; j--) {
                if (slot[j] == 0) { // If the slot is empty
                    slot[j] = jobs[i].jobID; // Assign the job to this slot
                    cnt++; // Increment the count of scheduled jobs
                    total += jobs[i].profit; // Add the profit of this job to the total
                    break; // Move to the next job
                }
            }
        }

        // Display the results
        System.out.println("Maximum profit is: " + total + " with the following job slots:");
        for (int i = 0; i < m; i++) {
            System.out.print(slot[i] + " "); // Display job IDs in their scheduled slots
        }
        System.out.println();
    }

    // Main function to execute the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of jobs
        System.out.println("Enter the number of jobs:");
        int n = sc.nextInt();

        // Array to store job details
        Job[] jobs = new Job[n];

        // Input job details (job ID, deadline, and profit)
        for (int i = 0; i < n; i++) {
            System.out.println("Enter job ID, deadline, and profit for job " + (i + 1) + ":");
            int jobID = sc.nextInt();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(jobID, deadline, profit); // Create a new Job object
        }

        // Create an instance of the main class and invoke the job scheduling function
        ass_2 obj = new ass_2();
        obj.final_ans(jobs, n); // Call the function to find the optimal job schedule
    }
}
