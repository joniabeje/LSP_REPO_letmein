package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Demonstrate all overloaded area methods with required output
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Demonstrate exception handling
        System.out.println("\nException demonstration:");
        try {
            AreaCalculator.area(-1.0); // This will throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /*
     * Overloading is the better design choice here because all methods perform the same
     * conceptual operation (calculating area) but with different parameters. This provides
     * a clean, intuitive API where users can call area() with the appropriate parameters
     * for their shape without needing to remember different method names like circleArea(),
     * rectangleArea(), etc. The compiler can determine which method to call based on the
     * parameter types and count, making the code more readable and maintainable.
     */
}
