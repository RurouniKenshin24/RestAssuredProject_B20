package day3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GithubRestAPITest {

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Github GET/users/{username}")
    @Test
    public void testGithub(){

        when()
                .get("https://api.github.com/users/{username}","RurouniKenshin24").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("server","GitHub.com")
                .body("login",is("RurouniKenshin24"));
    }

}
