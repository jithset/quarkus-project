package io.github.jithinsethu.superhero.restvillain;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VillainRepository implements PanacheRepository<Villain> {
}
