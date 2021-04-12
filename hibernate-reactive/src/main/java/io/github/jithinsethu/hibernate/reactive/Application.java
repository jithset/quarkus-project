package io.github.jithinsethu.hibernate.reactive;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logmanager.LogManager;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Application {

    private final Logger log = Logger.getLogger(Application.class.getName());
    private final PgPool client;
    private final boolean schemaCreate;

    public Application(PgPool client, @ConfigProperty(name = "myapp.schema.create", defaultValue = "true") boolean schemaCreate) {
        this.client = client;
        this.schemaCreate = schemaCreate;
    }

    void onStart(@Observes StartupEvent ev) {
        log.info("OnStart");
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        log.info("InitDB");
        client.query("DELETE FROM cart WHERE id = 1").execute()
                .flatMap(r -> client.query("INSERT INTO cart (id, name) VALUES (1, 'Cart 1')").execute())
                .await().indefinitely();
    }

}
