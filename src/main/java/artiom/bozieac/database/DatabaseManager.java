package artiom.bozieac.database;

import artiom.bozieac.auth.security.PasswordEncryptor;
import artiom.bozieac.utils.ApplicationProperties;
import artiom.bozieac.utils.ApplicationPropertiesConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Class that manages the database.
 */
public class DatabaseManager {
    /**
     * Logger instance.
     */
    private static final Logger logger = LogManager.getLogger(DatabaseManager.class);

    /**
     * Database url property.
     */
    private final String DB_URL = ApplicationProperties.getProperty(ApplicationPropertiesConstants.DB_URL);

    /**
     * Database username property.
     */
    private final String USERNAME = ApplicationProperties.getProperty(ApplicationPropertiesConstants.DB_USERNAME);

    /**
     * Database url password.
     */
    private final String PASSWORD = ApplicationProperties.getProperty(ApplicationPropertiesConstants.DB_PASSWORD);

    /**
     * Constructor.
     */
    public DatabaseManager() {
        initTables();
    }

    /**
     * Initializes tables. If they already exist doesn't do anything.
     */
    private void initTables() {
        try (final Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             final Statement stmt = conn.createStatement();
        ) {
            final String sql = "CREATE TABLE USERS " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " username VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        } catch (SQLSyntaxErrorException e) {
            if (logger.isInfoEnabled()) {
                logger.info("Tables already exist, not creating them again.");
            }
        } catch (SQLException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Tables couldn't have been created: ", e);
            }
        }
    }

    /**
     * Adds a new user.
     *
     * @param userUsername - User's username.
     * @param userPassword - User's password.
     */
    public void addUser(final String userUsername, final String userPassword) {
        try (final Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             final Statement stmt = conn.createStatement();
        ) {
            final String sql = "INSERT INTO users"
                    + "(username, password)"
                    + " values (\'" + userUsername + "\', \'" + userPassword + "\')";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            if (logger.isErrorEnabled()) {
                logger.error("User couldn't be registered: ", e);
            }
        }
    }

    /**
     * Returns user's encrypted password from DB.
     *
     * @param userUsername - The user's username.
     * @return User's encrypted password.
     */
    public String getUserPasswordFromDB(final String userUsername) {
        String encryptedPassword = null;

        try (final Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             final Statement stmt = conn.createStatement();
        ) {
            final String sql = "SELECT password FROM users WHERE username = \'" + userUsername + "\';";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                encryptedPassword = resultSet.getString("password");
            }
        } catch (SQLException e) {
            if (logger.isErrorEnabled()) {
                logger.error("User couldn't be logged in: ", e);
            }
        }

        return encryptedPassword;
    }

    /**
     * Checks if a username already exists.
     *
     * @param username - The username.
     * @return <code>true</code> or <code>false</code>
     */
    public boolean usernameExists(final String username) {
        String extractedUsername = null;

        try (final Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             final Statement stmt = conn.createStatement();
        ) {
            final String sql = "SELECT username FROM users WHERE username = \'" + username + "\';";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                extractedUsername = resultSet.getString("username");

                if (extractedUsername != null) {
                    return true;
                }
            }
        } catch (SQLException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Usernames couldn't be extracted: ", e);
            }
        }

        return false;
    }
}
