package artiom.bozieac.gui;

import artiom.bozieac.auth.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Code representation of Login Page GUI.
 */
public class LoginPage extends JFrame {

    /**
     * Login page panel.
     */
    private JPanel loginPagePanel;

    /**
     * Login page main label.
     */
    private JLabel loginPageLabel;

    /**
     * Username's field.
     */
    private JTextField usernameField;

    /**
     * Password's field.
     */
    private JPasswordField passwordField;

    /**
     * Login button.
     */
    private JButton loginButton;

    /**
     * Button that switches to the registration page.
     */
    private JButton registerPageButton;

    /**
     * User manager.
     */
    private final UserManager userManager;

    /**
     * Constructor to initialize Swing properties.
     */
    public LoginPage() {
        userManager = new UserManager();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String username = usernameField.getText();
                final String password = passwordField.getText();

                userManager.login(username, password);
            }
        });
    }

    /**
     * Main class.
     *
     * @param args - the arguments.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Audio Manager");

        frame.setContentPane(new LoginPage().loginPagePanel);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
