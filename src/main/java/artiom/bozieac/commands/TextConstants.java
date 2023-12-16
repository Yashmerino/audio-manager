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
            "help COMMAND - To print example of a command.\n";

    /**
     * Text to output for non-existing command.
     */
    public static final String SYNTAX_ERROR = "Syntax error. Command doesn't exist.\n";
}
