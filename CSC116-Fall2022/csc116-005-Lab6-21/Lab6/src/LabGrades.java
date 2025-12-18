import java.util.Scanner;

/**
 * Program calculates lowest lab grade, highest lab grade, sum of lab grades,
 * average lab grade, and median lab grade given a student's first four labs
 * 
 * @author Jessica Young Schmidt
 */
public class LabGrades {
    /**
     * Starts the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int lab1 = 100;
        int lab2 = 97;
        int lab3 = 78;
        int lab4 = 83;

        System.out.println("Grades:\n - Lab 1: " + lab1 + "\n - Lab 2: " + lab2 + "\n - Lab 3: "
                + lab3 + "\n - Lab 4: " + lab4);

        System.out.println("Stats:");
        System.out.println(" - Lowest Lab Grade: " + MathCalculations.min(lab1, lab2, lab3, lab4));
        System.out
                .println(" - Highest Lab Grade: " + MathCalculations.max(lab1, lab2, lab3, lab4));
        System.out
                .println(" - Sum of Lab Grades: " + MathCalculations.sum(lab1, lab2, lab3, lab4));
        System.out
                .println(" - Average Lab Grade: " + MathCalculations.mean(lab1, lab2, lab3, lab4));
        System.out.println(
                " - Median Lab Grade: " + MathCalculations.median(lab1, lab2, lab3, lab4));

        System.out.println();

        Scanner in = new Scanner(System.in);
        MathCalculations.calculations(in, 2);

        System.out.println();

        // Should throw exception
        MathCalculations.calculations(in, 5);
    }
}
