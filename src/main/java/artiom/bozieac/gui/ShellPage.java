package artiom.bozieac.gui;

import artiom.bozieac.commands.CommandsExecutor;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

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
                if (shellInput.getText().isBlank()) {
                    shellInput.setText("");
                    return;
                }

                if (!shellInput.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String[] parsedCommand = shellInput.getText().toLowerCase().split(" ");
                    final String command = parsedCommand[0];
                    shellInput.setText("");

                    CommandsExecutor.executeMethodForCommand(shellOutput, command, Arrays.copyOfRange(parsedCommand, 1, parsedCommand.length));
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
