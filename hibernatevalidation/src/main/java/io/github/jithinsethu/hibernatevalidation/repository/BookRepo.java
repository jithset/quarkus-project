package io.github.jithinsethu.hibernatevalidation.repository;

import io.github.jithinsethu.hibernatevalidation.models.Book;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepo implements PanacheRepository<Book> {
}
