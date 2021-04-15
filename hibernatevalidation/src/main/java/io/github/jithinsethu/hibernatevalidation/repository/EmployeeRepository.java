package io.github.jithinsethu.hibernatevalidation.repository;

import io.github.jithinsethu.hibernatevalidation.models.Author;
import io.github.jithinsethu.hibernatevalidation.models.Employee;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class EmployeeRepository implements PanacheRepository<Employee> {

    @Inject
    Mutiny.Session mutinySession;

    public Uni<Employee> findByName(String name) {
        return find("name", name).firstResult();
    }
}
