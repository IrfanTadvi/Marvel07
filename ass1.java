import java.util.Scanner;

public class ass1 {
    
    // Method for addition of two large numbers represented as strings
    public static String addition(String a, String b) {
        // Ensure a is the shorter string
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        String str = "";  // Result string to store the sum
        int n1 = a.length();
        int n2 = b.length();

        // Reverse both strings to make it easier to add starting from the least significant digit
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        int carry = 0; // Variable to store carry during addition
        // Add digits one by one from the end of both strings
        for (int i = 0; i < n1; i++) {
            int sum = ((a.charAt(i) - '0') + (b.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');  // Add the unit place of sum to result
            carry = sum / 10;  // Calculate the carry
        }

        // If the second number is longer, continue adding its remaining digits
        for (int i = n1; i < n2; i++) {
            int sum = ((b.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');  // Add the unit place of sum to result
            carry = sum / 10;  // Calculate the carry
        }
        
        // If there is a carry left after addition, add it
        if (carry != 0)
            str += (char)(carry + '0');

        // Reverse the result string to get the correct order
        str = new StringBuilder(str).reverse().toString();
        return str;
    }

    // Method for subtraction of two large numbers represented as strings
    static String diff(String a, String b) {
        String str = "";  // Result string to store the difference
        int n1 = a.length(), n2 = b.length();

        // Reverse both strings to make it easier to subtract starting from the least significant digit
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        int carry = 0; // Variable to store borrow during subtraction
        // Subtract digits one by one from the end of both strings
        for (int i = 0; i < n2; i++) {
            int sub = ((a.charAt(i) - '0') - (b.charAt(i) - '0') - carry);
            if (sub < 0) {  // If result is negative, borrow from the next digit
                sub += 10;
                carry = 1;  // Indicate a borrow
            } else
                carry = 0;  // No borrow
            str += sub;  // Add the result to the difference string
        }
        
        // Continue subtracting from the first number if the second number has been fully used
        for (int i = n2; i < n1; i++) {
            int sub = ((a.charAt(i) - '0') - carry);
            if (sub < 0) {  // If result is negative, borrow from the next digit
                sub += 10;
                carry = 1;
            } else
                carry = 0;
            str += sub;  // Add the result to the difference string
        }

        // Reverse the result string to get the correct order
        str = new StringBuilder(str).reverse().toString();
        return str;
    }

    // Method to remove leading zeros from a string
    public static String zeros(String str) {
        String pattern = "^0+(?!$)";  // Pattern to match leading zeros
        str = str.replaceAll(pattern, "");  // Replace leading zeros with an empty string
        return str;
    }

    // Method to multiply two large numbers using a recursive approach (Karatsuba Multiplication)
    public static String multiply(String A, String B) {
        // Ensure A is the shorter string
        if (A.length() > B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }
        int n1 = A.length(), n2 = B.length();
        
        // If the numbers are of different lengths, pad the shorter number with leading zeros
        while (n2 > n1) {
            A = "0" + A;
            n1++;
        }

        // Base case: If length of A is 1, just multiply the numbers directly
        if (n1 == 1) {
            int ans = Integer.parseInt(A) * Integer.parseInt(B);
            return Integer.toString(ans);
        }

        // If the length is odd, pad the numbers with an extra zero to make it even
        if (n1 % 2 == 1) {
            n1++;
            A = "0" + A;
            B = "0" + B;
        }

        // Split the numbers into two halves
        String al = "", ar = "", bl = "", br = "";
        for (int i = 0; i < n1 / 2; ++i) {
            al += A.charAt(i);  // Higher part of A
            bl += B.charAt(i);  // Higher part of B
            ar += A.charAt(n1 / 2 + i);  // Lower part of A
            br += B.charAt(n1 / 2 + i);  // Lower part of B
        }

        // Recursively multiply the higher and lower parts
        String p = multiply(al, bl);  // Product of higher parts
        String q = multiply(ar, br);  // Product of lower parts
        
        // Cross products
        String r = diff(multiply(addition(al, ar), addition(bl, br)), addition(p, q));

        // Shift the products accordingly (multiply p by 10^n, q by 10^(n/2), and r by 10^(n/2))
        for (int i = 0; i < n1; ++i)
            p = p + "0";  // Shift p by n places
        for (int i = 0; i < n1 / 2; ++i)
            r = r + "0";  // Shift r by n/2 places
        
        // Final result is the sum of all the shifted products
        String ans = addition(p, addition(q, r));
        ans = zeros(ans);  // Remove any leading zeros
        return ans;
    }

    // Main method for user input and choice-based operations
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Scanner to read input
        int choice;  // Variable to store user's choice

        do {
            // Display menu to the user
            System.out.println("Enter your choice:");
            System.out.println("1. Multiplication");
            System.out.println("2. Square");
            System.out.println("3. Addition");
            System.out.println("4. Subtraction");
            System.out.println("5. Exit");

            choice = sc.nextInt();  // Read user's choice
            sc.nextLine();  // Consume the newline character after integer input

            // Switch case based on user's choice
            switch (choice) {
                case 1:
                    // Option 1: Perform multiplication
                    System.out.println("Enter numbers A and B:");
                    String A = sc.nextLine();  // Read the first number
                    String B = sc.nextLine();  // Read the second number
                    System.out.println("Product: " + multiply(A, B));  // Call multiply method and print result
                    break;

                case 2:
                    // Option 2: Calculate the square of a number
                    System.out.println("Enter number A:");
                    A = sc.nextLine();  // Read the number
                    System.out.println("Square: " + multiply(A, A));  // Call multiply with the same number
                    break;

                case 3:
                    // Option 3: Perform addition
                    System.out.println("Enter numbers A and B:");
                    A = sc.nextLine();  // Read the first number
                    B = sc.nextLine();  // Read the second number
                    System.out.println("Sum: " + addition(A, B));  // Call addition method and print result
                    break;

                case 4:
                    // Option 4: Perform subtraction
                    System.out.println("Enter numbers A and B:");
                    A = sc.nextLine();  // Read the first number
                    B = sc.nextLine();  // Read the second number
                    System.out.println("Difference: " + diff(A, B));  // Call diff method and print result
                    break;

                case 5:
                    // Option 5: Exit the program
                    System.out.println("Exiting the program");
                    break;

                default:
                    // Invalid choice
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);  // Repeat the loop until user chooses to exit
        sc.close();  // Close the scanner
    }
}
