package artiom.bozieac.auth.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Class that encrypts the password.
 */
public class PasswordEncryptor {

    /**
     * Argon2 encrypter.
     */
    private final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

    /**
     * Encrypts the password
     *
     * @param password - The password.
     * @return The encrypted password.
     */
    public String encrypt(final String password) {
        return argon2.hash(2, 15 * 1024, 1, password.toCharArray());
    }

    /**
     * Verifies the password hash.
     *
     * @param password - The password.
     * @param hash     - The hash for the password.
     * @return <code>true</code> or <code>false</code>
     */
    public Boolean verifyPassword(final String password, final String hash) {
        try {
            return argon2.verify(hash, password.toCharArray());
        } catch (NullPointerException e) {
            return false;
        }
    }
}
