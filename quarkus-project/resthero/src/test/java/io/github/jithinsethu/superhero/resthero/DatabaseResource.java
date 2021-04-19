package io.github.jithinsethu.superhero.resthero;

import io.github.jithinsethu.superhero.utils.ConverterUtils;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {
    private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:9.6.12")
            .withDatabaseName("heroes_database")
            .withUsername("superman")
            .withPassword("superman")
            .withExposedPorts(8099);

    @Override
    public Map<String, String> start() {
        System.out.println("Starting Test Psql");

        DATABASE.start();
        if (DATABASE.isCreated()) {
            System.out.println("Test Database Started: "+ ConverterUtils.jdbcToReactive(DATABASE.getJdbcUrl()));
        }
        return Collections.singletonMap("quarkus.datasource.reactive.url", ConverterUtils.jdbcToReactive( DATABASE.getJdbcUrl()));
    }

    @Override
    public void stop() {
        if (DATABASE != null)
            DATABASE.stop();
    }
}
