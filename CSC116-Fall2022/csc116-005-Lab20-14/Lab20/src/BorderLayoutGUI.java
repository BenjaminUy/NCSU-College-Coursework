import java.awt.*;
import javax.swing.*;

/**
 * GUI application using BorderLayout
 * 
 * @author Jessica Young Schmidt
 */
public class BorderLayoutGUI extends JFrame {

    /**
     * Default constructor
     */
    public BorderLayoutGUI() {
        super("Border Layout GUI");
        setSize(800, 300);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a JPanel
        JPanel panel = new JPanel(new BorderLayout());
        // Add JPanel to the frame
        add(panel);

        panel.add(new JButton("Button 1"), BorderLayout.NORTH);
        panel.add(new JButton("2"), BorderLayout.SOUTH);
        panel.add(new JButton("Button 3"), BorderLayout.WEST);
        panel.add(new JButton("Long-Named Button 4"), BorderLayout.EAST);
        panel.add(new JButton("Button 5"), BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Starts the program
     * 
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        new BorderLayoutGUI();
    }
}