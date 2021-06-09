package io.github.jithinsethu.hibernate.reactive.cart;

import io.github.jithinsethu.hibernate.reactive.fruit.Fruit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    public Long id;

    @Getter @Setter
    public String name;

    @Getter @Setter
    @JsonbTransient
    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    public List<Fruit> fruits;

}
