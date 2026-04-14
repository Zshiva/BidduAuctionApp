package practice.projects.platform.constants;

/**
 * Security-related constants
 */
public class SecurityConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final long TOKEN_EXPIRATION_SECONDS = 3600L; // 1 hour
    public static final String JWT_ALGORITHM = "HS256";

    // Security error codes
    public static final String INVALID_TOKEN = "SEC001";
    public static final String EXPIRED_TOKEN = "SEC002";
    public static final String UNAUTHORIZED = "SEC003";
    public static final String FORBIDDEN = "SEC004";
    public static final String AUTHENTICATION_FAILED = "SEC005";
}

