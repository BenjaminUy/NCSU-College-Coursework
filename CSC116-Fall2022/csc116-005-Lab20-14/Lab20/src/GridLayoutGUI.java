import java.awt.*;
import javax.swing.*;

/**
 * GUI application using GridLayout
 * 
 * @author Jessica Young Schmidt
 */
public class GridLayoutGUI extends JFrame {

    /**
     * Default constructor
     */
    public GridLayoutGUI() {
        super("Grid Layout GUI");
        setSize(600, 100);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a JPanel
        JPanel panel = new JPanel(new GridLayout(3, 2));
        // Add JPanel to the frame
        add(panel);

        panel.add(new JButton("Button 1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("Button 3"));
        panel.add(new JButton("Long-Named Button 4"));
        panel.add(new JButton("Button 5"));

        setVisible(true);
    }

    /**
     * Starts the program
     * 
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        new GridLayoutGUI();
    }
}