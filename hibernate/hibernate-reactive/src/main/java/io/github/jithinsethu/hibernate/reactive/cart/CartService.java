package io.github.jithinsethu.hibernate.reactive.cart;

import io.github.jithinsethu.hibernate.reactive.fruit.Fruit;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.RowSet;
import java.util.List;

@ApplicationScoped
public class CartService {
    @Inject
    CartRepository cartRepository;

    public Uni<Cart> create(Long cartId, List<Fruit> fruitList) {
        return cartRepository.findById(cartId).onItem().transform(cart -> {
            fruitList.forEach(fruit -> {
                fruit.setCart(cart);
                cart.getFruits().add(fruit);
            });
            return cart;
        }).flatMap(item ->
                cartRepository.persistAndFlush(item).map(i -> item));
    }
}
