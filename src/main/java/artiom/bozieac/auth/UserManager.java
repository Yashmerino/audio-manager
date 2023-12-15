package artiom.bozieac.auth;

import artiom.bozieac.auth.security.PasswordEncryptor;
import artiom.bozieac.database.DatabaseManager;

/**
 * User manager class.
 */
public class UserManager {

    /**
     * Database manager to retrieve and update users.
     */
    private final DatabaseManager databaseManager = new DatabaseManager();

    /**
     * Password encryptor.
     */
    private final PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    /**
     * Registers a new user.
     *
     * @param username - The user's username.
     * @param password - The user's password.
     */
    public void register(final String username, final String password) {
        final String encryptedPassword = passwordEncryptor.encrypt(password);

        this.databaseManager.addUser(username, encryptedPassword);
    }

    /**
     * Login user.
     *
     * @param username - The user's username.
     * @param password - The user's password.
     */
    public boolean login(final String username, final String password) {
        String encryptedPassword = this.databaseManager.getUserPasswordFromDB(username);

        return this.passwordEncryptor.verifyPassword(password, encryptedPassword);
    }
}
