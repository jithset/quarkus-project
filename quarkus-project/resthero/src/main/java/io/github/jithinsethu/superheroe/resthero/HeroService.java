package io.github.jithinsethu.superheroe.resthero;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class HeroService {

    @Inject
    HeroRepository repository;

    public Uni<List<Hero>> findAllHeroes() {
        return repository.findAll().list();
    }

    public Uni<Hero> findHeroById(Long id) {
        return repository.findById(id);
    }

    public Uni<Hero> findRandomHero() {
        return repository.findAll().list().map(i ->
                {
                    int index = ThreadLocalRandom.current().nextInt(i.size());
                    return i.get(index);
                });
    }

    public Uni<Hero> persistHero(@Valid Hero hero) {
        return repository.persistAndFlush(hero).map(i -> hero);
    }

    public Uni<Hero> updateHero(@Valid Hero hero) {
        return Panache.withTransaction(() ->
            repository.findById(hero.id).map(h -> {
                h.name = hero.name;
                h.otherName = hero.otherName;
                h.level = hero.level;
                h.picture = hero.picture;
                h.powers = hero.powers;
                return h;
            }).map(h -> h)
        );
    }

    public Uni<Boolean> deleteHero(Long id) {
        return Panache.withTransaction(() -> repository.deleteById(id));
    }
}
