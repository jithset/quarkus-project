package io.github.jithinsethu.hibernate.reactive.fruit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jithinsethu.hibernate.reactive.cart.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
public class Fruit {


    public Fruit(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "name", length = 40, unique = true)
    public String name;

    @Getter @Setter
    @ManyToOne
    @JsonIgnore
    public Cart cart;
}
