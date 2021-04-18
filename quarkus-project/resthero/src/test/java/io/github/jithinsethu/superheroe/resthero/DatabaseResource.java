package io.github.jithinsethu.superheroe.resthero;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {
    private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:11.2")
            .withDatabaseName("heroes_database")
            .withUsername("superman")
            .withPassword("superman")
            .withExposedPorts(8086);

    @Override
    public Map<String, String> start() {
        DATABASE.start();
        return Collections.singletonMap("quarkus.datasource.reactive.url", DATABASE.getJdbcUrl());
    }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
