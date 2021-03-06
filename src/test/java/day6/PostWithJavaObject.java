package day6;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.spartan.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostWithJavaObject {


    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Add Data with POJO as a body")
    @Test
    public void testAddDataWithPOJO(){
        Spartan sp1 =SpartanUtil.getRandomSpartanPOJOPayload();

        given()
                .auth().basic("admin","admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);
    }
}
