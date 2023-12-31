package artiom.bozieac.gui;

import artiom.bozieac.auth.UserManager;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Code representation of Register Page GUI.
 */
public class RegisterPage extends JFrame {
    /**
     * Username field.
     */
    private JTextField usernameField;

    /**
     * Password field.
     */
    private JPasswordField passwordField;

    /**
     * Register page main label.
     */
    private JLabel registerPageLabel;

    /**
     * Register button.
     */
    private JButton registerButton;

    /**
     * Back to login page button.
     */
    private JButton backToLoginPageButton;

    /**
     * Register page panel.
     */
    private JPanel registerPagePanel;

    /**
     * User manager.
     */
    private UserManager userManager;

    /**
     * Constructor to initialize Swing properties.
     */
    public RegisterPage() {
        userManager = new UserManager();

        backToLoginPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setPanel(new LoginPage().getLoginPagePanel());
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().isBlank() || passwordField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty fields!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                if (!userManager.register(usernameField.getText(), passwordField.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Returns the register page panel.
     *
     * @return the register JPanel.
     */
    public JPanel getRegisterPagePanel() {
        return this.registerPagePanel;
    }
}
