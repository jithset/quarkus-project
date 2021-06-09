package org.acme.vertx.resources;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.acme.vertx.Fruit;
import org.acme.vertx.domains.Post;
import org.acme.vertx.domains.Users;
import org.acme.vertx.dtos.UsersDTO;
import org.acme.vertx.service.UsersService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class UserResource {

    @Inject
    UsersService usersService;

    /*@GET
    public Uni<List<UsersDTO>> create() {
//        Users users = new Users("Jithin", "S", "email");
        Users user1 = new Users("Jithin", "S", "email");
        Users user2 = new Users("Jithin1", "S", "email");
        Users user3 = new Users("Jithin2", "S", "email");
        Users user4 = new Users("Jithin3", "S", "email");
        return usersService.createUsers(Arrays.asList(user1, user2, user3, user4))
                .onItem().transform(UsersDTO::fromModelList);
    }*/

    @GET
    @Path("/multiple")
    public Uni<Users> createUsers() {
        Users user1 = new Users("Jithin", "S", "email");
        Post post = new Post();
        post.setDetail("Post 1 Detail");
        post.setUsers(user1);
        Post post1 = new Post();
        post1.setDetail("Post 2 Detail");
        post1.setUsers(user1);
//        user1.getPosts().add(post);
//        user1.getPosts().add(post1);
//        user1.getPosts().addAll(Arrays.asList(post, post1));


        user1.getPosts().addAll(Arrays.asList(post, post1));
        //user1.getPosts().add(Arrays.asList(post, post1));

        return usersService.createUserPost(user1, post);
//        return Uni.createFrom().item(user1);
    }

}
