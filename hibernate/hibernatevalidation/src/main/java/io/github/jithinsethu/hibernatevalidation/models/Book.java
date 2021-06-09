package io.github.jithinsethu.hibernatevalidation.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@ToString
public class Book {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @ManyToOne
    @Getter @Setter
    public Author author;
}
