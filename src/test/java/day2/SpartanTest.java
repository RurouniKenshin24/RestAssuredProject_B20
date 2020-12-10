package day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanTest {

    @DisplayName("Testing/ api/spartan endpoint")
    @Test
    public void testGetAllSpartan(){
        Response response = get("http://54.152.5.223:8000/api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(),equalTo(200));

        assertThat(response.contentType(), is(ContentType.JSON.toString()));
    }

}
