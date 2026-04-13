package practice.projects.platform.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing and verification using BCrypt
 */
public class PasswordHasher {
    private static final int LOG_ROUNDS = 12;

    /**
     * Hash a password using BCrypt
     * @param password the plain text password
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
    }

    /**
     * Verify a plain text password against a hashed password
     * @param password the plain text password
     * @param hashedPassword the hashed password
     * @return true if the password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

