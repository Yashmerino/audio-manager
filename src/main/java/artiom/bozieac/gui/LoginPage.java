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
     * The current frame to display.
     */
    protected static JFrame frame;

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

                if (userManager.login(username, password)) {
                    MainFrame.setPanel(new ShellPage().getShellPanel());
                }
            }
        });

        registerPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setPanel(new RegisterPage().getRegisterPagePanel());
            }
        });
    }

    /**
     * Returns login page panel.
     *
     * @return the login JPanel.
     */
    public JPanel getLoginPagePanel() {
        return this.loginPagePanel;
    }
}
