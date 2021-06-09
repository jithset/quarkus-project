package org.acme.vertx.domains;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Location {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "location")
    private List<Users> users;
}
