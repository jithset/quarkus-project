package io.github.jithinsethu.superheroe.resthero;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@QuarkusTest
public class HeroTest {
    @InjectMock
    HeroRepository heroRepository;

    @Test
    public void testPanacheMocking() {
        // Mocked classes always return a default value
        Assertions.assertEquals(0L, heroRepository.count().await().indefinitely());

        // Now let's specify the return value
        Mockito.when(heroRepository.count()).thenReturn(Uni.createFrom().item(4L));
        assertThat(4L).isEqualTo(heroRepository.count().await().indefinitely());

        // Now let's change the return value
        Mockito.when(heroRepository.count()).thenReturn(Uni.createFrom().item(42L));
        assertThat(42L).isEqualTo(heroRepository.count().await().indefinitely());

        // Now let's call the original method
//        Mockito.when(heroRepository.count()).thenCallRealMethod();
//        assertThat(0L).isEqualTo(heroRepository.count().await());

        // Check that we called it 4 times
        Mockito.verify(heroRepository, Mockito.times(3)).count();

        // Mock only with specific parameters
        Hero p = new Hero();
        Mockito.when(heroRepository.findById(12L)).thenReturn(Uni.createFrom().item(p));
        assertThat(heroRepository.findById(12L).await().indefinitely()).isEqualTo(Uni.createFrom().item(p).await().indefinitely());


        // Mock throwing
        Mockito.when(heroRepository.findById(12L)).thenThrow(new WebApplicationException());
        Assertions.assertThrows(WebApplicationException.class, () -> heroRepository.findById(12L));
        assertThatThrownBy(() ->heroRepository.findById(12L)).isInstanceOf(WebApplicationException.class);

        /*Mockito.when(heroRepository.findOrdered()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(heroRepository.findOrdered().isEmpty());

        // We can even mock your custom methods
        Mockito.verify(heroRepository).findOrdered();
        Mockito.verify(heroRepository, Mockito.atLeastOnce()).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(heroRepository);*/


    }
}