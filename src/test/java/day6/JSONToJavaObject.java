package day6;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.spartan.SpartanRead;
import utility.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

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

        SpartanRead sp =
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
                .getObject("", SpartanRead.class);

        System.out.println("sp = " + sp);
    }

    @DisplayName("Get All Data with save JSON Array as Java Object")
    @Test
    public void testGetSpartanArrayAsJavaObject() {

        Response response = given()
                                .auth().basic("admin","admin").
                            when()
                                .get("/spartans");

        JsonPath jp = response.jsonPath();

        List<SpartanRead> allSpartanPOJO = jp.getList("", SpartanRead.class);

        allSpartanPOJO.forEach(System.out::println);

        //This is the same thing with extract method in a long way!!!
/*        given()
                .auth().basic("admin","admin")
                .log().all()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
        .extract()
                .jsonPath()
                .get();
*/
    }

}
