package io.github.jithset.repository;

import io.github.jithset.models.Fruits;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitsRepository implements PanacheRepository<Fruits> {
}
