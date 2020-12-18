package day7;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.hr.Region;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HR_ORDS_Test {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.152.5.223:1000";
        basePath = "/ords/hr";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/regions/{region_id}")
    @Test
    public void testThirdRegionIsAsia(){

        given()
                .pathParam("region_id",3)
                .log().all().
        when()
                .get("/regions/{region_id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("region_name",is("Asia"));
    }

    @DisplayName("Testing Save GET/regions/{region_id} as POJO")
    @Test
    public void testSingleRegionToPOJO() {

        Region region =
                        given()
                                .pathParam("region_id", 3)
                                .log().all().
                        when()
                                .get("/regions/{region_id}")
                                .jsonPath()
                                .getObject("", Region.class);

        System.out.println("region = " + region);
    }

    @DisplayName("Testing Save GET/regions as List of POJO")
    @Test
    public void testSingleRegionToListPOJO() {

        List<Region> regionList =
                            given()
                                .log().all().
                            when()
                                .get("/regions")
                                .jsonPath()
                                .getList("items", Region.class);

        regionList.forEach(System.out::println);

        System.out.println("==============================");

        Response response = get("/regions");

        List<Region> newList = response.jsonPath().getList("items",Region.class);
        newList.forEach(System.out::println);
    }
}
