package io.github.jithinsethu.superhero.resthero;

import io.github.jithinsethu.superhero.utils.dtos.ResponseDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
public class HeroResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/heroes/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

    @Test
    public void shouldNotGetUnknownHero() {
        Long randomId = new Random().nextLong();
        given()
                .pathParam("id", randomId)
                .when().get("/api/heroes/{id}")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    private TypeRef<ResponseDTO> getHeroTypeRef() {
        return new TypeRef<>() {
            // Kept empty on purpose
        };
    }

    @Test
    void shouldPingOpenAPI() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .when().get("/openapi")
                .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void shouldPingSwaggerUI() {
        given()
                .when().get("/swagger-ui")
                .then()
                .statusCode(OK.getStatusCode());
    }



}