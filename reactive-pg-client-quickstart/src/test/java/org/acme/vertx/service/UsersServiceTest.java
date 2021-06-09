package org.acme.vertx.service;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.acme.vertx.domains.Users;
import org.acme.vertx.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Slf4j
public class UsersServiceTest {

    @Inject
    UserRepository repository;

    @Inject
    UsersService usersService;

    @Test
    public void test1() {
        log.info("Current Thread "+ Thread.currentThread().getName());
        Users users = new Users("Jithin", "S", "email");
        // Users u = repository.persist(users).chain(repository::flush).map(t -> users).await().indefinitely();
        usersService.createUser(users);
        //log.info(u.toString());
        Assertions.assertTrue(true);
    }

}