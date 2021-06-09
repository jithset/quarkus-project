package org.acme.vertx.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.acme.vertx.domains.Post;
import org.acme.vertx.domains.Users;
import org.acme.vertx.repository.PostRepository;
import org.acme.vertx.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class UsersService {

    @Inject
    UserRepository userRepository;
    @Inject
    PostRepository postRepository;

    public Uni<Users> createUser(Users users) {
        return Panache.withTransaction(() -> userRepository.persistAndFlush(users)).map(t -> users);
    }


//    public Uni<List<Users>> createUsers(List<Users> users) {
//        return repository.persist(users).chain(repository::flush).map(it -> users);
//    }

    public Uni<Users> createUserPost(Users user, Post post) {
        /*Users userCreate = new Users();
       userCreate.setFirstName(user.getFirstName());
       userCreate.setLastName(user.getLastName());
       userCreate.setEmail(user.getEmail());*/

//        Working
//       return postRepository.persistAndFlush(post).map(postid -> post)
//                .onItem().transform(t -> userRepository.persistAndFlush(user)).map(t-> user);

        return userRepository.persistAndFlush(user).map(it -> user);
        /*return userRepository.persistAndFlush(user).map(id -> user)
                .onItem().transform( t -> {

                    log.info("it val "+t);
                    Multi.createFrom().iterable(user.getPosts()).map(it -> {
                        it.setUsers(user);
                        return postRepository.persistAndFlush(it).onItem().transform(us -> user.getPosts().add(it));

                    });
                    *//*for (Post p : user.getPosts()) {

                        Uni.createFrom().item(p).map(po -> {
                            p.setUsers(user);
                            postRepository.persist(p).chain(postRepository::flush).map(us -> user.getPosts().add(p));

                            log.info("Post "+p.getDetail()+ p.getId());
                            return p;
                        });

                        postRepository.flush();
                    }*//*
//                    user.getPosts().forEach(ps -> {
//                                ps.setUsers(t);
//                                postRepository.persistAndFlush(ps);
//                            });
                    return user;
                });*/


       /*return userRepository.persistAndFlush(userCreate)
               .onItem().transform(x ->userCreate)
               .onItem().transformToUni(users ->
                postRepository.persistAndFlush(post).onItem().transform(t -> post)
                        .onItem().transform(t -> {
                    users.getPosts().add(t);
                    return userRepository.persistAndFlush(users).onItem().transform(y-> users);
                }).onItem().transform(t -> t)
               ).map(t-> user);
*/


    }
}
