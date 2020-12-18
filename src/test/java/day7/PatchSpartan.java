package day7;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.spartan.Spartan;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PatchSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Patch 1 Spartan with Java object")
    @Test
    public void testUpdateSpartan(){

//        Map<String,Object> patchBody = new LinkedHashMap<>();
//        patchBody.put("name","B20 Voilar");
//        patchBody.put("gender","Female");
//        patchBody.put("phone",1234657893L);

        Spartan sp = new Spartan();
        sp.setName("B20 Voilar");
        sp.setGender("Female");
        sp.setPhone(1237894561L);

        given()
                .auth().basic("admin","admin")
                .log().all()
                .pathParam("id",100)
                .contentType(ContentType.JSON)
                .body(sp).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);
    }
}
