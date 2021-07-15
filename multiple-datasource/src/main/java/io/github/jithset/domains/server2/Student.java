package io.github.jithset.domains.server2;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

    @Id
    @Column(name = "student_id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "student_name")
    @Getter @Setter
    private String name;
}
