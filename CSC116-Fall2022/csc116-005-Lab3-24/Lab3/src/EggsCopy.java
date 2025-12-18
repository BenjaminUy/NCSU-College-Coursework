import java.util.Scanner;

/**
 * Class gets input from user on how many eggs they ordered
 * @author Benjamin Uy
 */
public class EggsCopy {

    public static void main(String[] args) {
        // Construct input scanner for console
        Scanner console = new Scanner(System.in);

        // Prompt user for number of eggs
        System.out.print("Enter number of eggs in order: ");

        // Read in ordered eggs number
        int order = console.nextInt ();

        // Calculation dozen and loose egg quantity
        final int DOZEN = 12;
        int dozenquantity = order / DOZEN;
        int loosequantity = order % DOZEN;

        // Calculate total cost 
        final double DOZEN_COST = 3.25;
        final double LOOSE_COST = 0.45;
        double dozendollars = dozenquantity * DOZEN_COST;
        double loosedollars = loosequantity * LOOSE_COST;
        double total = dozendollars + loosedollars;

        // Print message
        System.out.printf("You ordered %d eggs. That is", order);
        System.out.printf(" %d dozen at $3.25 per dozen and", dozenquantity);
        System.out.printf(" %d loose eggs at 45 cents each", loosequantity);
        System.out.printf(" for a total of $%.2f.\n", total);
    }

}