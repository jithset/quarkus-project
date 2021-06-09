package org.acme.vertx;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.pgclient.PgPool;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class Application {

    @Inject
    PgPool pgPool;

    void onStart(@Observes StartupEvent ev) {
        System.out.println("The application is starting...");
        initDb();
    }

    private void initDb() {
        pgPool.query("DROP TABLE IF EXISTS fruits").execute()
                .flatMap(r -> pgPool.query("CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
                .flatMap(r -> pgPool.query("INSERT INTO fruits (name) VALUES ('Orange')").execute())
                .flatMap(r -> pgPool.query("INSERT INTO fruits (name) VALUES ('Pear')").execute())
                .flatMap(r -> pgPool.query("INSERT INTO fruits (name) VALUES ('Apple')").execute())
                .await().indefinitely();

    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("The application is stopping...");
    }

}
