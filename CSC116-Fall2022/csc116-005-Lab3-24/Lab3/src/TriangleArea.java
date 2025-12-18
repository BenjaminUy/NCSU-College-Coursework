import java.util.Scanner;

/** 
 * The class TriangleArea calculates the area of a triangle using 3 inputs from the user,
 * which are then plugged in to Heron's formula for the area of a triangle
 * 
 * @author unknown
 * edited by Benjamin Uy and Emma Gould
 */
public class TriangleArea {

    /** 
     * Starts the program
     * @param args arguments entered in the command line
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter side A of triangle (as int): ");
        /* Length of triangle side A */
        int sideA = in.nextInt();
        System.out.print("Enter side B of triangle (as int): ");
        /* Length of triangle side B */
        int sideB = in.nextInt();
        System.out.print("Enter side C of triangle (as int): ");
        /* Length of triangle side C */
        int sideC = in.nextInt();
        /* Holds value that is inside the square root in the formula for area of the triangle */
        double s = (sideA + sideB + sideC) / 2.0;
        /* Area of the triangle */
        double area = Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        System.out.println("The area of the triangle is: " + area);
    }

}
