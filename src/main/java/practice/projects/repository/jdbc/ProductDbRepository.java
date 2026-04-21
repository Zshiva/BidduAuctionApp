package practice.projects.repository.jdbc;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import practice.projects.repository.db.ProductDbEntity;

import java.util.List;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ProductDbRepository extends CrudRepository<ProductDbEntity, Long> {
    Optional<ProductDbEntity> findByName(String name);
    List<ProductDbEntity> findAll();
}

