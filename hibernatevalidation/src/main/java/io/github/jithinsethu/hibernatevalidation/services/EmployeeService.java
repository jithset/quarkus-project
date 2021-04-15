package io.github.jithinsethu.hibernatevalidation.services;

import io.github.jithinsethu.hibernatevalidation.models.Employee;
import io.github.jithinsethu.hibernatevalidation.repository.EmployeeRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    public Uni<Employee> create(Employee employee) {
        return repository.persist(employee).chain(repository :: flush).onItem().transform(none -> employee);
    }
}
