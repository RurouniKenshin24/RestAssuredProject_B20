package day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SingleSpartanTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/spartan/{id} endpoint")
    @Test
    public void singleSpartanTest(){

        when()
                .get("/spartans/34").
        then()
                .statusCode(200);

        given()
                .pathParam("id",34).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200);


        when()
                .get("/spartans/{id}",34).
        then()
                .statusCode(200);

        when()
                .get("/spartans/{id}",100).
        then()
                .statusCode(200)
        .body("id",is(100));
    }
}
