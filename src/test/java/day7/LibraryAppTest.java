package day7;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pojo.library.BookCategoryRead;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
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

    @DisplayName("Testing2 GET/dashboard_stats Endpoint")
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

    @DisplayName("Testing3 Save the result of GET/Dashboard_Stats as Map Object")
    @Test
    public void testGetDataAsMap(){

        Map<String,Object> responseAsMap =
        given()
                .log().all()
                .header("x-library-token",token).
        when()
                .get("/dashboard_stats")
                .jsonPath()
                .getMap("");

        System.out.println("responseAsMap = " + responseAsMap);
    }

    @DisplayName("Testing4 Save the result of GET/Book_Categories as Java Object")
    @Test
    public void testGetDataAsPOJO(){

        List<BookCategoryRead> books =
                                        given()
                                            .log().all()
                                            .header("x-library-token", token).
                                        when()
                                            .get("/get_book_categories")
                                            .jsonPath()
                                            .getList("", BookCategoryRead.class);

        books.forEach(System.out::println);

        //To save in POJO(Java object) 1 Json object use Json Path and getObject method!!!
        BookCategoryRead bookCategory =
                                        given()
                                                .log().all()
                                                .header("x-library-token", token).
                                        when()
                                                .get("/get_book_categories")
                                                .jsonPath()
                                                .getObject("[4]",BookCategoryRead.class);

        System.out.println("bookCategory = " + bookCategory);
    }
}
