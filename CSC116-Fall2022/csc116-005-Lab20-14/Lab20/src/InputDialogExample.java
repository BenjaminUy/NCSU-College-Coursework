import javax.swing.*;

/**
 * Simple example that uses JOptionPane showInputDialog method.
 * 
 * @author Reges and Stepp
 */
public class InputDialogExample {
    /**
     * Start program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "What's yer name, pardner?");
        JOptionPane.showMessageDialog(null, "Yeehaw, " + name);
    }
}