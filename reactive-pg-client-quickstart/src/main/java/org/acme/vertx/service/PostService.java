package org.acme.vertx.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.acme.vertx.domains.Post;
import org.acme.vertx.repository.PostRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PostService {

    @Inject
    PostRepository repository;

    public Uni<Post> create(Post post) {
         return Panache.withTransaction(() ->
             repository.persistAndFlush(post).map(p -> post)
         );
    }
}
