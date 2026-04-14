# BidduAuctionApp Security Implementation Guide

## Overview
This document describes the security implementation for BidduAuctionApp using Micronaut Security with JWT token-based authentication.

## Features Implemented

### 1. **Password Hashing with BCrypt**
- Passwords are hashed using BCrypt algorithm (12 salt rounds)
- Located in: `platform/security/PasswordHasher.java`
- Usage:
  ```java
  String hashedPassword = PasswordHasher.hashPassword(plainTextPassword);
  boolean isValid = PasswordHasher.verifyPassword(plainTextPassword, hashedPassword);
  ```

### 2. **JWT Token Generation**
- JWT tokens are generated upon successful authentication
- Token includes user email, roles, and expiration time
- Located in: `platform/security/JwtTokenProvider.java`
- Token expiration: 1 hour (configurable in `application.properties`)

### 3. **Custom Authentication Provider**
- Validates user credentials against user list
- Compares plain-text password with BCrypt-hashed password
- Located in: `platform/security/BidduAuthenticationProvider.java`

### 4. **Role-Based Access Control (RBAC)**
- Three roles: BIDDER, SELLER, ADMIN
- Endpoints are protected with `@Secured` annotations
- Configuration in: `application-security.yml`

### 5. **API Authentication Endpoints**
- Location: `controller/ApiLoginController.java`

#### Endpoints:

**POST /api/auth/login**
- Request: Query parameters (email, password)
- Response: JWT token with user details
- Example:
  ```bash
  curl -X POST "http://localhost:8080/api/auth/login?email=user@example.com&password=password123"
  ```
- Response:
  ```json
  {
    "code": "0",
    "message": "SUCCESS",
    "data": {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
      "type": "Bearer",
      "email": "user@example.com",
      "role": "BIDDER",
      "expiresIn": 3600
    }
  }
  ```

**GET /api/auth/verify**
- Requires: Valid JWT token in Authorization header
- Response: Token validity confirmation
- Example:
  ```bash
  curl -X GET "http://localhost:8080/api/auth/verify" \
    -H "Authorization: Bearer <TOKEN>"
  ```

**POST /api/auth/logout**
- Requires: Valid JWT token
- Response: Logout confirmation
- Note: Tokens remain valid until expiration. For true token invalidation, implement a token blacklist.

## Configuration

### JWT Configuration (application.properties)
```properties
micronaut.security.enabled=true
micronaut.security.token.jwt.enabled=true
micronaut.security.token.jwt.header=Authorization
micronaut.security.token.jwt.prefix=Bearer
micronaut.security.token.jwt.generator.expiration=3600
```

### URL Pattern Protection (application-security.yml)
```yaml
micronaut:
  security:
    intercept-url-map:
      - pattern: /api/auth/login
        access:
          - isAnonymous()
      - pattern: /bidder/**
        access:
          - hasRole("BIDDER")
      - pattern: /seller/**
        access:
          - hasRole("SELLER")
```

## How to Use

### 1. **Register a New User with Hashed Password**
```java
UserEntity user = new UserEntity();
user.setName("John Doe");
user.setEmail("john@example.com");
user.setPassword("myPassword123"); // Automatically hashed via setPassword()
user.setRoles(UserRole.BIDDER);
// Add to userList (or database in production)
UseCase.userList.add(user);
```

### 2. **Protect Controllers with Roles**
```java
@Controller("/bidder")
public class BidderController {
    
    @Get("/dashboard")
    @Secured("BIDDER") // Only BIDDER role can access
    public HttpResponse<?> dashboard() {
        return HttpResponse.ok("Bidder Dashboard");
    }
    
    @Post("/place-bid")
    @Secured("BIDDER") // Only BIDDER role can access
    public HttpResponse<?> placeBid(@Body BidRequest request) {
        // Implementation
        return HttpResponse.ok();
    }
}
```

### 3. **Multi-Role Access**
```java
@Secured({"BIDDER", "SELLER"}) // Both BIDDER and SELLER can access
public HttpResponse<?> viewProducts() {
    // Implementation
    return HttpResponse.ok();
}
```

### 4. **Admin-Only Access**
```java
@Secured("ADMIN")
public HttpResponse<?> getUserAnalytics() {
    // Only ADMIN can access
    return HttpResponse.ok();
}
```

## Security Best Practices Implemented

✅ **Password Hashing**: All passwords are BCrypt hashed with 12 rounds
✅ **JWT Tokens**: Short-lived tokens (1 hour) with minimal claims
✅ **Role-Based Access**: Granular access control at endpoint level
✅ **Bearer Token**: Standard HTTP Bearer token authentication
✅ **Error Messages**: Generic error messages to prevent info leakage

## Security Best Practices NOT YET Implemented

⚠️ **HTTPS**: Ensure all communication is over HTTPS in production
⚠️ **Token Refresh**: Consider implementing token refresh mechanism
⚠️ **Token Blacklist**: For logout functionality, implement token invalidation
⚠️ **Environment Secrets**: Store JWT secret in environment variables
⚠️ **CORS**: Configure CORS policy appropriately
⚠️ **Rate Limiting**: Add rate limiting to prevent brute force attacks
⚠️ **Audit Logging**: Log authentication attempts and failed logins
⚠️ **Two-Factor Authentication**: Consider adding 2FA for enhanced security

## Future Enhancements

1. **OAuth2/OIDC Integration**: Support third-party authentication (Google, GitHub)
2. **Multi-Factor Authentication**: Add SMS or authenticator app verification
3. **Session Management**: Implement persistent session store
4. **API Key Authentication**: For service-to-service communication
5. **Request Signing**: Add request signature validation for critical operations
6. **Encryption**: Encrypt sensitive data at rest

## Testing Security

```java
// Test JWT token generation
@Test
void testJwtTokenGeneration() {
    JwtTokenProvider tokenProvider = new JwtTokenProvider(...);
    String token = tokenProvider.generateToken("user@test.com", List.of("BIDDER"));
    assertNotNull(token);
    assertTrue(token.startsWith("eyJ"));
}

// Test password hashing
@Test
void testPasswordHashing() {
    String password = "securePassword123";
    String hashed = PasswordHasher.hashPassword(password);
    assertTrue(PasswordHasher.verifyPassword(password, hashed));
    assertFalse(PasswordHasher.verifyPassword("wrongPassword", hashed));
}

// Test authentication
@Test
void testAuthenticationFails() {
    // Test with wrong credentials
    assertEquals(401, loginWithWrongPassword());
}
```

## References

- [Micronaut Security Documentation](https://docs.micronaut.io/latest/guide/index.html#security)
- [JWT Best Practices](https://tools.ietf.org/html/rfc8949)
- [OWASP Authentication Guidelines](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)
- [BCrypt Algorithm](https://en.wikipedia.org/wiki/Bcrypt)

