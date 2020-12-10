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

public class SpartanTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing/ api/spartan endpoint")
    @Test
    public void testGetAllSpartan(){
        Response response = get("/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(),equalTo(200));

        assertThat(response.contentType(), is(ContentType.JSON.toString()));
    }

    @DisplayName("Testing/ api/spartan endpoint XML")
    @Test
    public void testGetAllSpartanXML(){
        given()
                .header("accept","application/xml").
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .header("Content-Type","application/xml");

        //This is the same request but in a easier way!!!
        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .contentType(ContentType.XML);
    }

}
