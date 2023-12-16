package artiom.bozieac.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that processes the command
 */
public class CommandsProcessor {

    /**
     * Regex pattern to extract arguments from a command.
     */
    private final static Pattern pattern = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");

    /**
     * Returns array of arguments
     *
     * @param command - The command with arguments.
     * @return Array of String.
     */
    public static String[] getArguments(final String command) {
        List<String> args = new ArrayList<>();
        Matcher matcher = pattern.matcher(command);

        while (matcher.find()) {
            args.add(matcher.group(1).replace("\"", ""));
        }

        return args.subList(1, args.size()).toArray(new String[0]);
    }

    /**
     * Returns the command from input.
     *
     * @param command - The command.
     * @return String.
     */
    public static String getCommand(final String command) {
        return command.toLowerCase().split(" ")[0];
    }
}
