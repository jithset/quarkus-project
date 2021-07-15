package io.github.jithset.domains.server1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "employee_name")
    @Getter @Setter
    private String name;
}
