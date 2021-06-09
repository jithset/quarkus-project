package org.acme.vertx.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import org.acme.vertx.domains.Post;
import org.acme.vertx.domains.Users;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {
}
