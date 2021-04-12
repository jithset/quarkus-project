package io.github.jithinsethu.hibernate.reactive.cart;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartRepository implements PanacheRepository<Cart> {
}
