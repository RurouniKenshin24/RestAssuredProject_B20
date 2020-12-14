package day4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OmdAPI {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://www.omdbapi.com";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Searching Movie or OMDAPI")
    @Test
    public void testMovie(){
        given()
                .queryParam("t","The Lord Of The Rings")
                .queryParam("apikey","5b5d0fe8").
        when()
                .get().
        then()
                .statusCode(200)
                .body("Title",is("The Lord of the Rings"))
                .body("Ratings[0].Source",is("Internet Movie Database"));
    }

    @DisplayName("Getting log of request and response")
    @Test
    public void testSendingRequestAngGetLog(){
        given()
                .queryParam("t","John Wick")
                .queryParam("apikey","5b5d0fe8")
                .log().all().
        when()
                .get().
        then()
                .log().all()
                .statusCode(200)
                .body("Plot", containsString("ex-hit-man"))
                .body("Ratings[1].Source",is("Rotten Tomatoes"));
    }
}
