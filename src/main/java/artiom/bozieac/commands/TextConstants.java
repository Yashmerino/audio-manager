package artiom.bozieac.commands;

/**
 * Class that holds constants for text output in shell.
 */
public class TextConstants {

    /**
     * Text to output for command HELP.
     */
    public final static String HELP = "Existing commands:\n" +
            "help - To print existing commands.\n" +
            "cdir - To print current directory.\n" +
            "cd PATH - To set a new directory.\n" +
            "ls - To print audio files in the current directory.\n" +
            "ls PATH - To print audio files in the PATH directory.\n" +
            "play FILE - To play the FILE in the current directory.\n" +
            "search REGEX - To search for the file in the current directory and its subdirectories using Regex.\n" +
            "rename NAME NEW_NAME - To rename NAME file to NEW_NAME.\n" +
            "clear - To clear the shell output.\n";

    /**
     * Text to output for non-existing command.
     */
    public static final String SYNTAX_ERROR = "Syntax error. Command doesn't exist.\n";

    /**
     * Text to output for non-existing path.
     */
    public static final String PATH_DOESNT_EXIST = "Path doesn't exist.\n";
}
