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

public class UpdateSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing PUT/api/spartans/{id} String body")
    @Test
    public void test1UpdateSpartan(){
        String updatePayload = "{\n" +
                "        \"name\": \"OguzhanCelik\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 6529873215\n" +
                "    }";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id","109")
                .body(updatePayload).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
                .header("Date",is(notNullValue()))
                .body(emptyString());

    }

    @DisplayName("Testing PATCH/api/spartans/{id} String word")
    @Test
    public void test2PartialUpdateSpartan(){
        String patchPayload = "{\n" +
                "        \"name\": \"OguzhanB20\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id","109")
                .body(patchPayload).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
                .header("Date",is(notNullValue()))
                .body(emptyString());

    }
}
