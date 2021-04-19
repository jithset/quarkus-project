package io.github.jithinsethu.superhero.resthero;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class HeroTest {
    @InjectMock
    HeroRepository heroRepository;

    @Test
    public void testPanacheMocking() {
        Assertions.assertEquals(0L, heroRepository.count().await().indefinitely());

        Mockito.when(heroRepository.count()).thenReturn(Uni.createFrom().item(4L));
        assertThat(4L).isEqualTo(heroRepository.count().await().indefinitely());

        Mockito.when(heroRepository.count()).thenReturn(Uni.createFrom().item(42L));
        assertThat(42L).isEqualTo(heroRepository.count().await().indefinitely());

        Mockito.verify(heroRepository, Mockito.times(3)).count();

        Hero p = new Hero();
        Mockito.when(heroRepository.findById(12L)).thenReturn(Uni.createFrom().item(p));
        assertThat(heroRepository.findById(12L).await().indefinitely()).isEqualTo(Uni.createFrom().item(p).await().indefinitely());

        Mockito.when(heroRepository.findById(12L)).thenThrow(new WebApplicationException());
        Assertions.assertThrows(WebApplicationException.class, () -> heroRepository.findById(12L));
        assertThatThrownBy(() ->heroRepository.findById(12L)).isInstanceOf(WebApplicationException.class);



    }
}