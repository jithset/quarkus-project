package io.github.jithinsethu.hibernate.reactive.fruit;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FruitService {

    @Inject
    FruitRepository repository;

    public Uni<List<Fruit>> getAllFruits() {
        return repository.listAll(Sort.by("name"));
    }

    public Uni<Fruit> getAFruit(Long id) {
        return repository.findById(id);
    }

    public Uni<Fruit> save(Fruit fruit) {
        return Panache.withTransaction(() -> repository.persist(fruit).map(t -> fruit));
    }

    public Uni<Fruit> update(Long id, Fruit fruit) {
        return Panache
                .withTransaction(() -> repository.findById(id)
                        .onItem().ifNotNull().invoke(entity -> entity.name = fruit.name)
                );
    }

    public Uni<Boolean> delete(Long id) {
        return Panache
                .withTransaction(() -> repository.deleteById(id));
    }
}
