package io.github.jithinsethu.hibernatevalidation.services;

import io.github.jithinsethu.hibernatevalidation.models.Author;
import io.github.jithinsethu.hibernatevalidation.models.Book;
import io.github.jithinsethu.hibernatevalidation.models.Employee;
import io.github.jithinsethu.hibernatevalidation.repository.AuthorRepo;
import io.github.jithinsethu.hibernatevalidation.repository.BookRepo;
import io.github.jithinsethu.hibernatevalidation.repository.EmployeeRepository;
import io.github.jithinsethu.hibernatevalidation.resources.BookResource;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

@ApplicationScoped
public class BookService {
    private static final Logger LOG = Logger.getLogger(BookService.class.getName());
    @Inject
    BookRepo bookRepo;

    @Inject
    AuthorRepo authorRepo;

    @Inject
    EmployeeRepository employeeRepository;


    public Uni<Book> createBook(Book book) {
        System.out.println("Current thread "+ Thread.currentThread().getName());
        Employee emp =new Employee();
        emp.setName("Name of Emp");
        System.out.println("Employee "+ emp);
        return authorRepo.findByName(book.author.getName())
                .onItem().ifNotNull()
                .transform(author -> {
                    book.setAuthor(author);
                    author.setBooks(Arrays.asList(book));
//                    bookRepo.persist(book);
//                    authorRepo.persist(author);
                    return author;
                }).chain(bookRepo::flush).onItem().transform(m -> book);
    }
}
