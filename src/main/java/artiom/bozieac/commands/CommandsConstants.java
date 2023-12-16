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
     * Command constant for CDIR.
     */
    public final static String CDIR = "cdir";

    /**
     * Command constant for CD.
     */
    public final static String CD = "cd";

    /**
     * Map that links command to text to output.
     */
    protected final static Map<String, String> commandToTextMap = new HashMap<>();


    static {
        commandToTextMap.put(HELP, TextConstants.HELP);
    }
}
