package day8;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.hr.Region;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class HR_ORDS_API_DB_Test extends HR_ORDS_TestBase {

    @DisplayName("Testing the connection with query")
    @Test
    public void testDB_Connection(){
        DB_Utility.runQuery("select * from regions");
        DB_Utility.displayAllData();
    }

    @DisplayName("Testing GET/regions/{region_id} result")
    @Test
    public void testDB_API_ResultsAsMap(){

        Region region = given()
                            .pathParam("region_id",3).
                        when()
                            .get("/regions/{region_id}").
                        then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .jsonPath()
                            .getObject("",Region.class);

        DB_Utility.runQuery("select * from regions");
        Map<String,String> thirdRow = DB_Utility.getRowMap(3);

        assertThat(region.getRegion_id() + "",equalTo(thirdRow.get("REGION_ID")));
        assertThat(region.getRegion_name() + "",equalTo(thirdRow.get("REGION_NAME")));
    }

    @DisplayName("Testing GET/regions/{region_id} Result match with Database value by value")
    @Test
    public void testDB_API_ResultsAsJson(){

        Response response = given()
                .pathParam("region_id",3).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

//        DB_Utility.runQuery("select * from regions");
//        Map<String,String> thirdRow = DB_Utility.getRowMap(3);

//        assertThat(region_id,is(thirdRow.get("REGION_ID")));
//        assertThat(region_name,is(thirdRow.get("REGION_NAME")));

        String region_id = "" + response.jsonPath().getInt("region_id");
        String region_name = response.jsonPath().getString("region_name");

        int myId = 3;
        DB_Utility.runQuery("select region_id,region_name from regions where region_id = " + myId);
        String expectedRegion_id = DB_Utility.getColumnDataAtRow(1,1);
        String expectedRegion_Name = DB_Utility.getColumnDataAtRow(1,2);

        assertThat(region_id,is(expectedRegion_id));
        assertThat(region_name,is(expectedRegion_Name));

    }
}
