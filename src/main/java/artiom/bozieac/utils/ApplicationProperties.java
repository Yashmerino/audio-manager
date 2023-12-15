package artiom.bozieac.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that manages application properties.
 */
public class ApplicationProperties {

    /**
     * Logger instance.
     */
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

    /**
     * Path to the application's properties.
     */
    private static final String APPLICATION_PROPERTIES_PATH = "src/main/resources/application.properties";

    /**
     * Application properties object.
     */
    private static final Properties applicationProperties = new Properties();

    static {
        try {
            applicationProperties.load(new FileInputStream(APPLICATION_PROPERTIES_PATH));
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Properties couldn't be loaded.", e);
            }
        }
    }

    /**
     * Returns a property.
     *
     * @param propertyName - Property's name.
     * @return The property.
     */
    public static String getProperty(final String propertyName) {
        return applicationProperties.getProperty(propertyName);
    }
}
