import java.awt.*;
import javax.swing.*;

/**
 * GUI application using BoxLayout
 * 
 * @author Jessica Young Schmidt
 */
public class BoxLayoutGUI extends JFrame {

    /**
     * Default constructor
     */
    public BoxLayoutGUI() {
        super("Box Layout GUI");
        setSize(300, 300);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box = Box.createVerticalBox();
        add(box);

        box.add(new JButton("Button 1"));
        box.add(new JButton("2"));
        box.add(new JButton("Button 3"));
        box.add(new JButton("Long-Named Button 4"));
        box.add(new JButton("Button 5"));

        setVisible(true);
    }

    /**
     * Starts the program
     * 
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        new BoxLayoutGUI();
    }
}