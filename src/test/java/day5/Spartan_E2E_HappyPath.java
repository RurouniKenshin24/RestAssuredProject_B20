package day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class Spartan_E2E_HappyPath {

    private static Map<String,Object> payload;
    private static int newID;

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
        payload = SpartanUtil.getRandomSpartanPayload();
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("1.Testing POST/api/spartans Endpoint")
    @Test
    public void testAddData(){

        newID =
        given()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name",is(payload.get("name")))
                .body("data.gender",is(payload.get("gender")))
                .body("data.phone",is(payload.get("phone")))
        .extract()
                .jsonPath()
                .getInt("data.id");

        System.out.println(newID);
    }

    @DisplayName("2.Testing GET/api/spartans/{id} Endpoint")
    @Test
    public void testGetData(){

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .log().all().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(newID))
                .body("name",is(payload.get("name")))
                .body("gender",is(payload.get("gender")))
                .body("phone",is(payload.get("phone")));
    }

    @DisplayName("3.Testing PUT/api/spartans/{id} Endpoint")
    @Test
    public void testUpdateData(){
        payload = SpartanUtil.getRandomSpartanPayload();

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all().
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
                .body(emptyString());

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .log().all().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(newID))
                .body("name",is(payload.get("name")))
                .body("gender",is(payload.get("gender")))
                .body("phone",is(payload.get("phone")));

    }

    @DisplayName("4.Testing DELETE/api/spartans/{id} Endpoint")
    @Test
    public void testDeleteData() {
        payload = SpartanUtil.getRandomSpartanPayload();

        given()
                .auth().basic("admin", "admin")
                .pathParam("id", newID)
                .log().all().
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
                .body(emptyString());

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .log().all().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(404);
    }
}
