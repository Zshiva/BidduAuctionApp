 # BidduAuctionApp

Micronaut (Java 17) auction web application with Handlebars views and a simple Clean Architecture-style structure (`controller/`, `usecase/`, `repository/`, `platform/`).

> Note: This project currently uses an in-memory `userList` (see `practice.projects.platform.usecase.UseCase`). For production, migrate to Micronaut Data + a real database.

## Features

- Micronaut 4.x, Java 17
- MVC views using Handlebars (`src/main/resources/views`)
- Use-case driven business logic (`src/main/java/practice/projects/usecase`)
- Email template rendering (Freemarker)
- **Security (added):**
  - **BCrypt password hashing** (`PasswordHasher`)
  - **JWT authentication** (Bearer tokens)
  - **Role-based access control** (`BIDDER`, `SELLER`, `ADMIN`)

## Project Structure (high level)

```
src/main/java/practice/projects/
  controller/         # HTTP controllers (views + API endpoints)
  converter/          # payload -> usecase request conversion
  repository/         # entities / persistence models (currently in-memory)
  usecase/            # business use-cases
  platform/           # shared utilities, constants, exceptions, security, rest models

src/main/resources/
  views/              # .hbs templates
  emailTemplates/     # freemarker templates
  application.properties
  application-security.yml
```

## Security (JWT + BCrypt)

Security is enabled with Micronaut Security JWT.

### What was implemented

- Passwords are stored hashed (BCrypt) via `UserEntity#setPassword(...)`.
- JWT tokens are generated via `JwtTokenProvider`.
- Sensitive endpoints are protected with `@Secured`.

### Important files

- `src/main/java/practice/projects/platform/security/PasswordHasher.java`
- `src/main/java/practice/projects/platform/security/JwtTokenProvider.java`
- `src/main/java/practice/projects/platform/security/BidduAuthenticationProvider.java`
- `src/main/resources/application.properties`
- `src/main/resources/application-security.yml`
- Extra docs: `SECURITY.md`, `SECURITY_QUICKSTART.md`

### API Authentication endpoints

`ApiLoginController` exposes:

- `POST /api/auth/login?email=...&password=...` → returns a JWT token
- `GET  /api/auth/verify` (requires `Authorization: Bearer <token>`)
- `POST /api/auth/logout` (requires `Authorization: Bearer <token>`; note: currently informational only)

Example:

```bash
curl -X POST "http://localhost:8080/api/auth/login?email=user@example.com&password=pass123"

curl -X GET "http://localhost:8080/api/auth/verify" \
  -H "Authorization: Bearer <TOKEN_FROM_LOGIN>"
```

### Role-based protection (examples)

- `/bidder` requires role `BIDDER`
- `/seller` and `/seller/addproduct` require role `SELLER`
- `/bidproduct` requires role `BIDDER`

## Build & Run

### Requirements

- Java 17+
- Maven

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/BidduAuctionApp-0.1.jar
```

App should start on `http://localhost:8080` (default Micronaut port).

## Testing

```bash
mvn test
```

## References

- Micronaut 4.1.5 User Guide: https://docs.micronaut.io/4.1.5/guide/index.html
- Micronaut Security: https://docs.micronaut.io/latest/guide/index.html#security
- Micronaut Maven Plugin: https://micronaut-projects.github.io/micronaut-maven-plugin/latest/
- Handlebars Views: https://micronaut-projects.github.io/micronaut-views/latest/guide/index.html#handlebars


