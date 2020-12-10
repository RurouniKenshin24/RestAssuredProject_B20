package day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredIntro {

    @DisplayName("Hello RestAssured")
    @Test
    public void spartanTest1(){

        //http://54.152.5.223:8000/api/hello

        Response response = get("http://54.152.5.223:8000/api/hello");
        System.out.println(response.toString());
        System.out.println(response.body());

        response.prettyPrint();

        String responseStr = response.prettyPrint();
        assertThat(responseStr,is("Hello from Sparta"));

        System.out.println("Status code is " + response.statusCode());

        assertThat(response.statusCode(), is(200));

        assertThat(response.contentType(),containsString("text"));

        assertThat(response.contentType(),startsWith(ContentType.TEXT.toString()));
    }

}
