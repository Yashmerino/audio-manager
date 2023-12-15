package artiom.bozieac.gui;

import javax.swing.*;

/**
 * Class that holds the main JFrame.
 */
public class MainFrame extends JFrame {

    /**
     * Current frame.
     */
    private static JFrame frame;

    /**
     * Sets the current panel.
     */
    public static void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Main class.
     *
     * @param args - the arguments.
     */
    public static void main(String[] args) {
        frame = new JFrame("Audio Manager");

        frame.setContentPane(new LoginPage().getLoginPagePanel());
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
