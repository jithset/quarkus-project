package io.github.jithinsethu.hibernate.reactive.fruit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fruit {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "name", length = 40, unique = true)
    public String name;
}
