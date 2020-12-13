package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @DisplayName("Extracting data from Spartan JSON array object")
    @Test
    public void test2SpartanPayload(){

        JsonPath jp = get("/spartans").jsonPath();

        for (int i = 0;i < 10;i++){
            System.out.println(jp.getString("name[" + i + "]"));
        }

        System.out.println("============================");

        List<String> nameList = jp.getList("name");
        System.out.println("Names = " + nameList);

    }

    @DisplayName("Searching data from Spartan JSON array object")
    @Test
    public void testSearchSpartan(){

        JsonPath jp1 = get("/spartans/search?nameContains=a&gender=Male").jsonPath();

        String name = jp1.getString("content[0].name");
        System.out.println("First Guy name:" + name);

        //This is the same thing in a different way!!!
        JsonPath jp2 = given()
                            .queryParam("nameContains","le")
                            .queryParam("gender","Male").
                        when()
                            .get("/spartans/search").jsonPath();

        System.out.println("Second Guy name:" + jp2.getString("content[1].name"));
        System.out.println(jp2.getString("content.name"));
        System.out.println(jp2.getString("content.phone"));

    }

}
