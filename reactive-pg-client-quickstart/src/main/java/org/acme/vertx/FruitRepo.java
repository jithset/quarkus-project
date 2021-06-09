package org.acme.vertx;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.SqlClientHelper;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FruitRepo {

    @Inject
    PgPool pgPool;

    public Multi<Fruit> getAll() {
        System.out.println("Thread Current "+ Thread.currentThread().getName());
        Uni<RowSet<Row>> rowSet = pgPool.query("SELECT id, name FROM fruits ORDER BY name ASC").execute();
        return rowSet.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(FruitRepo::from);
    }

    public Uni<Fruit> findById(Long id) {
        return pgPool.preparedQuery("SELECT id, name FROM fruits WHERE id = $1").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    public Uni<Long> save(String name) {
        return pgPool.preparedQuery("INSERT INTO fruits (name) VALUES ($1) RETURNING id").execute(Tuple.of(name))
                .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    public Uni<Void> insertTwoFruits(Fruit fruit1, Fruit fruit2) {
        return SqlClientHelper.inTransactionUni(pgPool, tx -> {
            Uni<RowSet<Row>> insertOne = tx.preparedQuery("INSERT INTO fruits (name) VALUES ($1) RETURNING id")
                    .execute(Tuple.of(fruit1.getName()));
            Uni<RowSet<Row>> insertTwo = tx.preparedQuery("INSERT INTO fruits (name) VALUES ($1) RETURNING id")
                    .execute(Tuple.of(fruit2.getName()));

            return Uni.combine().all().unis(insertOne, insertTwo).discardItems();
        });
    }




    private static Fruit from(Row row) {
        return new Fruit(row.getLong("id"), row.getString("name"));
    }

}
