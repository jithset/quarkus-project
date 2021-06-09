package io.github.jithinsethu.hibernatevalidation.services;

import io.github.jithinsethu.hibernatevalidation.models.Author;
import io.github.jithinsethu.hibernatevalidation.repository.AuthorRepo;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class AuthorService {

    @Inject
    AuthorRepo authorRepo;

    public Uni<List<Author>> getAllAuthors() {
        return authorRepo.findAll().list();
    }

    public Uni<Author> saveAuthor(Author author) {
        return authorRepo.persistAndFlush(author).map(t->author);
    }

    public Uni<Author> findByName(String name) {
        return authorRepo.findByName(name);
    }
}
