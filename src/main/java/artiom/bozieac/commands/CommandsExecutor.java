package artiom.bozieac.commands;

import artiom.bozieac.utils.AudioExtensions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class that executes commands.
 */
public class CommandsExecutor {

    /**
     * Logger instance.
     */
    private static final Logger logger = LogManager.getLogger(CommandsExecutor.class);

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
    private static void help() {
        shellOutput.append(CommandsConstants.commandToTextMap.get(CommandsConstants.HELP));
    }

    /**
     * Executes the command "cdir" - current directory.
     */
    private static void cdir() {
        shellOutput.append(currentDirectory + "\n");
    }

    /**
     * Executes the command "cd" - set directory.
     */
    private static void cd(String... args) {
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
     *
     * @param args - The args.
     */
    private static void ls(final String... args) {
        File directory = new File(currentDirectory);

        if (args.length > 0) {
            directory = new File(args[0]);
        }

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
     * Executes the command "play" - play audio file.
     *
     * @param args - The args.
     */
    private static void play(final String... args) {
        if (args.length > 0) {
            final File audio = new File(currentDirectory, args[0]);

            try {
                Desktop.getDesktop().open(audio);
            } catch (IllegalArgumentException e) {
                shellOutput.append(audio.getName() + " couldn't be played. The file doesn't exist.");
            } catch (IOException e) {
                shellOutput.append(audio.getName() + " couldn't be played. The file may be corrupt.");
            }
        }
    }

    /**
     * Executes the command "rename" - rename audio file.
     *
     * @param args - The args.
     */
    private static void rename(final String... args) {
        if (args.length > 1) {
            final File audio = new File(currentDirectory, args[0]);
            final File newAudio = new File(currentDirectory, args[1]);

            if (!audio.renameTo(newAudio)) {
                shellOutput.append("File couldn't be renamed.\n");
            }
        }
    }

    /**
     * Executes the command "clear" - clears the shell output.
     */
    private static void clear() {
        shellOutput.setText("");
    }

    /**
     * Executes the command "search" - search for regex in directories and subdirectories.
     *
     * @param args - The arguments.
     */
    private static void search(String... args) {
        if (args.length > 0) {
            final Pattern pattern = Pattern.compile(args[0]);
            final File directory = new File(currentDirectory);

            try {
                List<Path> filesList = Files.walk(directory.toPath()).filter(Files::isRegularFile).toList();

                boolean found = false;
                for (Path file : filesList) {
                    final String filename = file.toFile().getName();
                    if (pattern.matcher(filename).matches()) {
                        shellOutput.append(file.toFile().getAbsolutePath() + "\n");
                        found = true;
                    }
                }

                if (!found) {
                    shellOutput.append("No matches found.\n");
                }
            } catch (IOException e) {
                shellOutput.append("Search couldn't be performed.\n");
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
                ls(args);
            }
            case CommandsConstants.PLAY -> {
                play(args);
            }
            case CommandsConstants.RENAME -> {
                rename(args);
            }
            case CommandsConstants.CLEAR -> {
                clear();
            }
            case CommandsConstants.SEARCH -> {
                search(args);
            }
            default -> {
                shellOutput.append(TextConstants.SYNTAX_ERROR);
            }
        }
    }
}
