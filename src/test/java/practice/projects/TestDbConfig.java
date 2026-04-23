package practice.projects;

import io.micronaut.test.support.TestPropertyProvider;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Starts a PostgreSQL Testcontainer and wires Micronaut datasource properties for tests.
 */
public abstract class TestDbConfig implements TestPropertyProvider {

    private static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("biddu_auction_test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    static {
        POSTGRES.start();
    }

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("datasources.default.url", POSTGRES.getJdbcUrl());
        props.put("datasources.default.username", POSTGRES.getUsername());
        props.put("datasources.default.password", POSTGRES.getPassword());
        props.put("datasources.default.driverClassName", POSTGRES.getDriverClassName());
        props.put("datasources.default.dialect", "POSTGRES");
        props.put("flyway.datasources.default.enabled", "true");
        return props;
    }
}

