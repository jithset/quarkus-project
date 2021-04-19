package io.github.jithinsethu.superhero.resthero;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HeroRepository implements PanacheRepository<Hero> {
}
