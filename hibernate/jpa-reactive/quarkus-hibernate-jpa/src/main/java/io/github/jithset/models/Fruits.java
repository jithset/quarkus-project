package io.github.jithset.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "fruits")
public class Fruits {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

}
