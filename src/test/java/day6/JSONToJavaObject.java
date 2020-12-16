package day6;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utility.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONToJavaObject {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Get JSON Data with save as Java Object")
    @Test
    public void testGetSpartanAsJavaObject(){

        Map<String,Object> payloadMap=
        given()
                .auth().basic("admin","admin")
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("id",5).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).
        extract()
                .jsonPath()
                .getMap("");

        System.out.println("payloadMap = " + payloadMap);

        given()
                .auth().basic("admin","admin")
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("id",5).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).
        extract()
                .jsonPath()
                //.getObject("",String)
        ;

    }
}
