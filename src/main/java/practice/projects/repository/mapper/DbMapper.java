package practice.projects.repository.mapper;

import practice.projects.platform.constants.UserRole;
import practice.projects.repository.ProductEntity;
import practice.projects.repository.UserEntity;
import practice.projects.repository.db.ProductDbEntity;
import practice.projects.repository.db.UserDbEntity;

public final class DbMapper {

    private DbMapper() {
    }

    public static UserDbEntity toDbEntity(UserEntity domain) {
        UserDbEntity db = new UserDbEntity();
        db.setName(domain.getName());
        db.setEmail(domain.getEmail());
        // domain currently stores hashed password in getPassword()
        db.setPasswordHash(domain.getPassword());
        db.setContact(domain.getContact());
        db.setAddress(domain.getAddress());
        db.setRole(domain.getRoles() != null ? domain.getRoles().name() : null);
        return db;
    }

    public static UserEntity toDomain(UserDbEntity db) {
        UserEntity domain = new UserEntity();
        domain.setName(db.getName());
        domain.setEmail(db.getEmail());
        // DO NOT call domain.setPassword() here because it hashes; we already have hash.
        domain.setContact(db.getContact());
        domain.setAddress(db.getAddress());
        if (db.getRole() != null) {
            domain.setRoles(UserRole.valueOf(db.getRole()));
        }

        // set hashed password directly via reflection-free approach isn't available.
        // Use setter would re-hash, so we set the field using a dedicated method.
        setHashedPassword(domain, db.getPasswordHash());
        return domain;
    }

    private static void setHashedPassword(UserEntity domain, String hashed) {
        try {
            var field = UserEntity.class.getDeclaredField("password");
            field.setAccessible(true);
            field.set(domain, hashed);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to set hashed password on UserEntity", e);
        }
    }

    public static ProductDbEntity toDbEntity(ProductEntity domain) {
        ProductDbEntity db = new ProductDbEntity();
        db.setName(domain.getName());
        db.setDescription(domain.getDescription());
        db.setEntryTime(domain.getEntryTime());
        db.setExpiryTime(domain.getExpiryTime());
        db.setMinBiddingAmount(domain.getMinBiddingAmount());
        return db;
    }

    public static ProductEntity toDomain(ProductDbEntity db) {
        return new ProductEntity(
                db.getName(),
                db.getDescription(),
                db.getEntryTime(),
                db.getExpiryTime(),
                db.getMinBiddingAmount()
        );
    }
}

