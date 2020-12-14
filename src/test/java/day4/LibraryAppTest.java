package day4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    private static String token;

    @BeforeAll
    public static void setUp(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing1 POST/login Endpoint")
    @Test
    public void testLogin(){
        String librarianUserName = "librarian69@library";
        String password = "KNPXrm3S";

        token =
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email",librarianUserName)
                .formParam("password",password).
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("token");

        System.out.println(token);
    }

    @DisplayName("Testing2 GET/dashboard_status Endpoint")
    @Test
    public void testDashboard_Status(){

        given()
                .log().all()
                .header("x-library-token",token).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(200);
    }


}
