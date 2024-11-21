# Function implementing Karatsuba Multiplication Algorithm
def algo(num1, num2):
    # Base case: If either number is single-digit, return the direct multiplication
    if num1 < 10 or num2 < 10:
        return num1 * num2

    # Determine the maximum length of the two numbers
    n = max(len(str(num1)), len(str(num2)))
    # Calculate the midpoint of the digits
    half = n // 2

    # Split the first number into two parts: `a` (higher part) and `b` (lower part)
    a = num1 // (10 ** half)  # Integer division to get the higher half
    b = num1 % (10 ** half)  # Modulo to get the lower half

    # Split the second number into two parts: `c` (higher part) and `d` (lower part)
    c = num2 // (10 ** half)  # Integer division to get the higher half
    d = num2 % (10 ** half)  # Modulo to get the lower half

    # Recursive calls to calculate the three necessary products:
    ac = algo(a, c)  # Product of the higher parts
    bd = algo(b, d)  # Product of the lower parts
    ad_bc = algo(a + b, c + d) - ac - bd  # Cross product of mixed terms

    # Combine the results using the formula:
    # result = ac * 10^(2*half) + ad_bc * 10^(half) + bd
    result = ac * (10 ** (2 * half)) + ad_bc * (10 ** half) + bd

    return result  # Return the final multiplication result

# Infinite loop to repeatedly prompt the user for operations
while True:
    # Prompt the user for their choice
    c1 = int(input("Enter Your Choice: (1: For Multiplication) (2: For Square): "))

    # Option 1: Perform multiplication of two numbers
    if c1 == 1:
        num1 = int(input("Enter No.1: "))  # Input the first number
        num2 = int(input("Enter No.2: "))  # Input the second number
        result = algo(num1, num2)  # Call the Karatsuba algorithm
        print("Ans is: ", result)  # Print the result

    # Option 2: Calculate the square of a number (using the same algorithm)
    if c1 == 2:
        num1 = int(input("Enter No.: "))  # Input the number to be squared
        result = algo(num1, num1)  # Square is equivalent to multiplying the number by itself
        print("Ans is: ", result)  # Print the result
