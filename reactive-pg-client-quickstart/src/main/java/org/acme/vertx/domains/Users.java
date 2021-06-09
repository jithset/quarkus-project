package org.acme.vertx.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jboss.logging.annotations.Pos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Users {

    public Users(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;


    @ManyToOne
    @Getter @Setter
    private Location location;

    @Getter @Setter
    private String email;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Post> posts = new ArrayList<>();




}
