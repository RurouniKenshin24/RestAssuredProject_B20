package day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.hr.Country;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HR_ORDS_API_DB_PracticeTest extends HR_ORDS_TestBase {

    @DisplayName("GET/countries/{country_id} Compare with DB")
    @Test
    public void testResponseMatchDB(){
        String country_id = "AR";

        Country country = given()
                                .pathParam("country_id",country_id)
                                .log().all().
                            when()
                                .get("/countries/{country_id}")
                                .as(Country.class);

        String actualArgentina = country.getCountry_name();
        System.out.println("actualArgentina = " + actualArgentina);

        DB_Utility.runQuery("select country_name from countries where country_id = '" + country_id +"'");
        String expectedArgentina = DB_Utility.getColumnDataAtRow(1,1);
        System.out.println("expectedArgentina = " + expectedArgentina);

        assertThat(actualArgentina,is(expectedArgentina));
    }

    @DisplayName("GET/countries/{country_id} Compare with DB")
    @Test
    public void testMapResponseMatchDB(){
        List<String> allCountries = get("/countries").jsonPath().getList("items.country_name");

        DB_Utility.runQuery("select country_name from countries");
        List<String> expectedAllCountries = DB_Utility.getColumnDataAsList("country_id");

        assertThat(allCountries,is(expectedAllCountries));

    }
}
