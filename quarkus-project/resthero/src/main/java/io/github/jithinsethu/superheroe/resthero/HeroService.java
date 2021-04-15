package io.github.jithinsethu.superheroe.resthero;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Random;

@ApplicationScoped
public class HeroService {

    @Inject
    HeroRepository repository;

    public Multi<Hero> findAllHeroes() {
        return repository.findAll().stream();
    }

    public Uni<Hero> findHeroById(Long id) {
        return repository.findById(id);
    }

    public Uni<Hero> findRandomHero() {
        return repository.findAll().count().flatMap(id-> {
           int randId =  new Random().nextInt(id.intValue());
           return repository.findById((long) randId);
        }).map(t -> t);
    }

    public Uni<Hero> persistHero(@Valid Hero hero) {
        return repository.persistAndFlush(hero).map(i -> hero);
    }

    public Uni<Hero> updateHero(@Valid Hero hero) {
        return repository.findById(hero.id).map(h -> {
            h.name = hero.name;
            h.otherName = hero.otherName;
            h.level = hero.level;
            h.picture = hero.picture;
            h.powers = hero.powers;
            return h;
        });
    }

    public Uni<Boolean> deleteHero(Long id) {
        return repository.deleteById(id);
    }
}
