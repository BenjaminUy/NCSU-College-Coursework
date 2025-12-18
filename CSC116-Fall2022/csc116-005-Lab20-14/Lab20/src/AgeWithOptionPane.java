import javax.swing.*;

/**
 * Calculates number of years until 70
 * 
 * @author Jessica Young Schmidt
 */
public class AgeWithOptionPane {
    /**
     * Starts the program
     * 
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        String ageText = JOptionPane.showInputDialog(null, "How old are you?");
        int age = -1;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer.");
            System.exit(1);
        }
        JOptionPane.showMessageDialog(null, "You'll be 70 in " + (70 - age) + " years.");
    }

}