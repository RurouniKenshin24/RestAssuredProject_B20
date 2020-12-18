package day8;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.hr.Country;
import pojo.hr.Region;
import testbase.HR_ORDS_TestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class HR_ORDS_Test extends HR_ORDS_TestBase {

    @DisplayName("Testing Save GET/countries/{country_id} as POJO")
    @Test
    public void testSingleCountryToPOJO() {

        Country country =
                        given()
                                .pathParam("country_id", "BE")
                                .log().all().
                        when()
                                .get("/countries/{country_id}")
                                .jsonPath()
                                .getObject("", Country.class);

        System.out.println("Country = " + country);

        System.out.println("===============================");

        Response response = given()
                                .pathParam("country_id","BE")
                            .when()
                                .get("/countries/{country_id}")
                                .prettyPeek();

        JsonPath jp = response.jsonPath();
        Country country2 = jp.getObject("", Country.class);

        System.out.println("Country2 = " + country2);

    }

    @DisplayName("Testing Save GET/regions as List of POJO")
    @Test
    public void testSingleRegionToListPOJO() {

        List<Country> countryList =
                            given()
                                .log().all().
                            when()
                                .get("/countries")
                                .jsonPath()
                                .getList("items", Country.class);

        countryList.forEach(System.out::println);

        System.out.println("==============================");

        Response response = get("/countries");

        List<Country> newList = response.jsonPath().getList("items",Country.class);
        newList.forEach(System.out::println);
    }
}
