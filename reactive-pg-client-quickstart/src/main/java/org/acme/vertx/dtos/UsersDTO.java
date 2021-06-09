package org.acme.vertx.dtos;

import lombok.Getter;
import lombok.Setter;
import org.acme.vertx.domains.Location;
import org.acme.vertx.domains.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private Location location;

    @Getter @Setter
    private String email;

    public static UsersDTO fromModel(Users users) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.id = users.getId();
        usersDTO.email = users.getEmail();
        usersDTO.firstName = users.getFirstName();
        usersDTO.lastName = users.getLastName();
        return usersDTO;
    }

    public static List<UsersDTO> fromModelList(List<Users> users) {
        return users.stream().map(UsersDTO::fromModel).collect(Collectors.toList());
    }
}
