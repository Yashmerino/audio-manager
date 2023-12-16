package artiom.bozieac.gui;

import artiom.bozieac.commands.CommandsConstants;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Code representation of Shell page GUI.
 */
public class ShellPage {

    /**
     * Shell panel.
     */
    private JPanel shellPanel;

    /**
     * Shell's output.
     */
    private JTextArea shellOutput;

    /**
     * Shell's input.
     */
    private JTextField shellInput;

    /**
     * Constructor to initialize Swing properties.
     */
    public ShellPage() {
        shellInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String command = shellInput.getText().toLowerCase();
                    shellInput.setText("");

                    if (command.equals(CommandsConstants.HELP)) {
                        shellOutput.append(CommandsConstants.getTextForCommand(CommandsConstants.HELP));
                    } else {
                        shellOutput.append(CommandsConstants.getTextForCommand(command));
                    }
                }
            }
        });
    }

    /**
     * Returns shell panel.
     *
     * @return Shell JPanel.
     */
    public JPanel getShellPanel() {
        return this.shellPanel;
    }
}
