package day4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Adding Spartan")
    @Test
    public void testPostSpartan(){
        String newSpartanStr = "    {\n" +
                            "        \"name\": \"jango\",\n" +
                            "        \"gender\": \"Female\",\n" +
                            "        \"phone\": 6529873210\n" +
                            "    }";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(newSpartanStr).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .header("Date",is(not("null")))
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("jango"))
                .body("data.gender",is("Female"))
                .body("data.phone",is(6529873210L));
    }

    @DisplayName("Adding Spartan with Map")
    @Test
    public void testPostSpartanAsMap(){

        Map<String,Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name","Tucky");
        payloadMap.put("gender","Male");
        payloadMap.put("phone",6529873210L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(payloadMap).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .header("Date",is(not("null")))
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Tucky"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(6529873210L));
    }

    @DisplayName("Adding Spartan with External Json File")
    @Test
    public void testPostSpartanExternalFile(){
        File file = new File("singleSpartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .header("Date",is(not("null")))
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Oguzhan"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(6529873215L));

    }
}
