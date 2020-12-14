package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExtractDataPractice {
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

        JsonPath js =
        given()
                .auth().basic("admin","admin")
                .log().all()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .statusCode(200)
             .extract()
                .jsonPath();

        List<String> names = js.getList("content.name");
        System.out.println(names);

        int numberOfElements = js.getInt("numberOfElements");
        assertThat(names.size(), equalTo(numberOfElements));
    }

}
