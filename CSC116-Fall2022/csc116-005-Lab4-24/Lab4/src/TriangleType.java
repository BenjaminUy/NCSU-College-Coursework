import java.util.Scanner;

/**
 * The class TriangleType determines what kind of triangle is formed
 * by the side lengths input by the user.
 * @author Benjamin Uy and Emma Gould
 */
public class TriangleType {

        /** Starts the program
         * @param args arguments from command line
         */

    public static void main(String[] args){
        Scanner sideLength = new Scanner(System.in);
        System.out.println("Triangle program provides the type of any triangle.");
        System.out.println("Please enter the lengths of sides a, b, and c.");

        System.out.print("Enter side a: ");
        /* Length of side A */
        int sideA = sideLength.nextInt ();

        System.out.print("Enter side b: ");
        /* Length of side B */
        int sideB = sideLength.nextInt ();

        System.out.print("Enter side c: ");
        /* Length of side C */
        int sideC = sideLength.nextInt ();
        
        // Triangle side lengths are now stored in variables

        /* Length of the longest side in the triangle */
        int longestSide = Math.max(sideA, Math.max(sideB, sideC));

        int sumOthers = ((sideA + sideB + sideC) - longestSide);

        /* constant representing the number of sides in a triangle, used for 
         * computations in the conditionals 
         */
        final int sidesTriangle = 3;


        if (longestSide >= sumOthers) {
            // if condition is true, the side lengths cannot form a triangle
            System.out.println("Not a valid triangle");

        } else if ((sideA <= 0) || (sideB <= 0) || (sideC <= 0)) {
            // if any sides are negative, triangle is not valid
            System.out.println("Not a valid triangle");

        } else if (((sideA + sideB + sideC) / sidesTriangle) == sideA) {
            // uses average of side lengths to determine if triangle is equilateral
            System.out.println("Equilateral triangle");

        } else if ((sideA == sideB) || (sideB == sideC) || (sideA == sideC)) {
            // checks if only two side lengths are equal; returns isosceles
            System.out.println("Isosceles triangle");
            
        } else {
            /* 
            * at this point the triangle is valid and all side lengths are
            * different; returns scalene
            */ 
            System.out.println("Scalene triangle");
        } // if
    } // main method
} // TriangleType class
