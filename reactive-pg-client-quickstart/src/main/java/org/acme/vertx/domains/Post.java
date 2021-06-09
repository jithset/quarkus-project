package org.acme.vertx.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private LocalDate createdDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonbTransient
    @JsonIgnore
    private Users users;

    @Getter @Setter
    private String detail;
}
