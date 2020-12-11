package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonPathIntro {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Extracting data out of Spartan JSON object")
    @Test
    public void test1SpartanPayload(){

        Response response = given()
                                .accept(ContentType.JSON).
                            when()
                                .get("/spartans/{id}",34);

        //It returns String object!!!
        response.prettyPrint();
        //It returns Response object!!!
        response.prettyPeek();

        System.out.println("============================");

        JsonPath jp = response.jsonPath();
        int spartanId = jp.getInt("id");
        String spartanName = jp.getString("name");
        String spartanGender = jp.getString("gender");
        long spartanPhone = jp.getLong("phone");

        System.out.println(spartanId);
        System.out.println(spartanName);
        System.out.println(spartanGender);
        System.out.println(spartanPhone);


    }

}
