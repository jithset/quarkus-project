package io.github.jithinsethu.superheroe.resthero;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jithinsethu.superheroe.resthero.dtos.ResponseDTO;
import io.github.jithinsethu.superheroe.utils.ConverterUtils;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.smallrye.mutiny.Uni;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeroResourceTest {

    private static final String DEFAULT_NAME = "Super Baguette";
    private static final String UPDATED_NAME = "Super Baguette (updated)";
    private static final String DEFAULT_OTHER_NAME = "Super Baguette Tradition";
    private static final String UPDATED_OTHER_NAME = "Super Baguette Tradition (updated)";
    private static final String DEFAULT_PICTURE = "super_baguette.png";
    private static final String UPDATED_PICTURE = "super_baguette_updated.png";
    private static final String DEFAULT_POWERS = "eats baguette really quickly";
    private static final String UPDATED_POWERS = "eats baguette really quickly (updated)";
    private static final int DEFAULT_LEVEL = 42;
    private static final int UPDATED_LEVEL = 43;

    private static final int NB_HEROES = 3;
    private static String heroId;

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

    @Test
    public void shouldGetRandomHero() {
        given()
                .when().get("/api/heroes/random")
                .then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON);
    }

    @Test
    public void shouldNotAddInvalidItem() {
        Hero hero = new Hero();
        hero.name = null;
        hero.otherName = DEFAULT_OTHER_NAME;
        hero.picture = DEFAULT_PICTURE;
        hero.powers = DEFAULT_POWERS;
        hero.level = 0;

        given()
                .body(hero)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/api/heroes")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode());
    }

    /*@Test
    @Order(1)
    public void shouldGetInitialItems() {
        given().get("/api/heroes").then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(containsString("Chewbacca"));
    }*/

    /*@Test
    @Order(1)
    public void shouldGetInitialItems() {
        RestAssured.defaultParser = Parser.JSON;
        Response response = get("/api/heroes")
                .then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .extract().response();


                *//*.then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(it -> it).;*//*
        String temp =response.print();
        System.out.println();
        assertEquals(NB_HEROES, 951);
    }*/

    /*@Test
    @Order(1)
    public void shouldGetInitialItems() {
        ResponseDTO heroes = get("/api/heroes").then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .extract().body().as(getHeroTypeRef());

        assertEquals(NB_HEROES,  ConverterUtils.convertObjectToList(heroes.data).size());
    }*/

    /*@Test
    @Order(2)
    public void shouldAddAnItem() {
        Hero hero = new Hero();
        hero.name = DEFAULT_NAME;
        hero.otherName = DEFAULT_OTHER_NAME;
        hero.picture = DEFAULT_PICTURE;
        hero.powers = DEFAULT_POWERS;
        hero.level = DEFAULT_LEVEL;

        ResponseDTO resObj = given()
                .body(hero)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/api/heroes")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().body().as(getHeroTypeRef());
        assertTrue(resObj.status);


        LinkedHashMap heroCreateRes = (LinkedHashMap) resObj.data;
        Integer heroId = (Integer) heroCreateRes.get("id");
        // Stores the id
        assertNotNull(heroId);

        given()
                .pathParam("id", heroId.longValue())
                .when().get("/api/heroes/{id}")
                .then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body("name", Is.is(DEFAULT_NAME))
                .body("otherName", Is.is(DEFAULT_OTHER_NAME))
                .body("level", Is.is(DEFAULT_LEVEL))
                .body("picture", Is.is(DEFAULT_PICTURE))
                .body("powers", Is.is(DEFAULT_POWERS));

        ResponseDTO heroes = get("/api/heroes").then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getHeroTypeRef());
        assertEquals(NB_HEROES + 1, ConverterUtils.convertObjectToList(heroes.data).size());
    }
*/






    private TypeRef<ResponseDTO> getHeroTypeRef() {
        return new TypeRef<>() {
            // Kept empty on purpose
        };
    }



}