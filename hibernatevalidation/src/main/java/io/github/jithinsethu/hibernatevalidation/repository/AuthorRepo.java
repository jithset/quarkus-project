package io.github.jithinsethu.hibernatevalidation.repository;

import io.github.jithinsethu.hibernatevalidation.models.Author;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AuthorRepo implements PanacheRepository<Author> {

    public Uni<Author> findByName(String name) {
        return find("name", name).firstResult();
    }
}
