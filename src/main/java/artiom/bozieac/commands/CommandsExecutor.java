package artiom.bozieac.commands;

import artiom.bozieac.utils.AudioExtensions;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;

/**
 * Class that executes commands.
 */
public class CommandsExecutor {

    /**
     * JTextArea which holds the shell output.
     */
    private static JTextArea shellOutput;

    /**
     * Holds the current directory.
     */
    private static String currentDirectory = FileSystems.getDefault().getPath(".").toFile().getAbsolutePath();

    /**
     * Executes the command "help" - help
     */
    public static void help() {
        shellOutput.append(CommandsConstants.commandToTextMap.get(CommandsConstants.HELP));
    }

    /**
     * Executes the command "cdir" - current directory.
     */
    public static void cdir() {
        shellOutput.append(currentDirectory + "\n");
    }

    /**
     * Executes the command "cd" - set directory.
     */
    public static void cd(String... args) {
        if (args.length > 0) {
            final File newDirectory = new File(args[0]);

            if (!newDirectory.exists()) {
                shellOutput.append(TextConstants.PATH_DOESNT_EXIST);
            } else {
                currentDirectory = newDirectory.getAbsolutePath();
            }
        }
    }

    /**
     * Executes the command "ls" - list audio files.
     */
    public static void ls() {
        final File directory = new File(currentDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (AudioExtensions.isAudioFile(file.getName())) {
                    shellOutput.append(file.getName() + "\n");
                }
            }
        }
    }

    /**
     * Executes the method linked to the command.
     *
     * @param shellOutput - The JTextArea that stores the shell output.
     * @param command     - The command.
     * @param args        - The arguments.
     */
    public static void executeMethodForCommand(final JTextArea shellOutput, final String command, String... args) {
        CommandsExecutor.shellOutput = shellOutput;

        switch (command) {
            case CommandsConstants.HELP -> {
                help();
            }
            case CommandsConstants.CDIR -> {
                cdir();
            }
            case CommandsConstants.CD -> {
                cd(args);
            }
            case CommandsConstants.LS -> {
                ls();
            }
            default -> {
                shellOutput.append(TextConstants.SYNTAX_ERROR);
            }
        }
    }
}
