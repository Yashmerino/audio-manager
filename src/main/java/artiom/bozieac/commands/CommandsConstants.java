package artiom.bozieac.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that holds commands constants.
 */
public class CommandsConstants {

    /**
     * Command constant for HELP.
     */
    public final static String HELP = "help";

    /**
     * Map that links command to text to output.
     */
    private final static Map<String, String> commandToTextMap = new HashMap<>();

    static {
        commandToTextMap.put(HELP, TextConstants.HELP);
    }

    /**
     * Returns text that should be printed in the shell for a command.
     *
     * @param command - The command.
     * @return <code>String</code>
     */
    public static String getTextForCommand(final String command) {
        return commandToTextMap.get(command);
    }
}
