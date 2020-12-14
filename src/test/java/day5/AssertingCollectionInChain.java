package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertingCollectionInChain {
    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.90.101.103:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/ api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData(){

        ;
        given()
                .auth().basic("admin","admin")
                .log().all()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
                when()
                .get("/spartans/search").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("numberOfElements", equalTo(184))
                .body("content",hasSize(184))
                .body("content.name",everyItem(containsStringIgnoringCase("a")))
                .body("content.gender",everyItem(is("Female")));
    }

}
