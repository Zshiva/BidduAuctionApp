package practice.projects.repository.jdbc;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import practice.projects.repository.db.UserDbEntity;

import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UserDbRepository extends CrudRepository<UserDbEntity, Long> {
    Optional<UserDbEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}

