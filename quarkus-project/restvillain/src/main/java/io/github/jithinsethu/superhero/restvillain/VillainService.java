package io.github.jithinsethu.superhero.restvillain;

import io.github.jithinsethu.superhero.restvillain.Villain;
import io.github.jithinsethu.superhero.restvillain.VillainRepository;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class VillainService {
    @ConfigProperty(name = "level.multiplier", defaultValue="1")
    int levelMultiplier;

    @Inject
    VillainRepository repository;

    public Uni<List<Villain>> findAllVillain() {
        return repository.findAll().list();
    }

    public Uni<Villain> findVillainById(Long id) {
        return repository.findById(id);
    }

    public Uni<Villain> findRandomVillain() {
        return repository.findAll().list().map(i ->
                {
                    int index = ThreadLocalRandom.current().nextInt(i.size());
                    return i.get(index);
                });
    }

    public Uni<Villain> persistVillain(@Valid Villain villain) {
        return repository.persistAndFlush(villain).map(i -> villain);
    }

    public Uni<Villain> updateVillain(@Valid Villain villain) {
        return Panache.withTransaction(() ->
            repository.findById(villain.id).map(h -> {
                h.name = villain.name;
                h.otherName = villain.otherName;
                h.level = villain.level * levelMultiplier;
                h.picture = villain.picture;
                h.powers = villain.powers;
                return h;
            }).map(h -> h)
        );
    }

    public Uni<Boolean> deleteVillain(Long id) {
        return Panache.withTransaction(() -> repository.deleteById(id));
    }
}
