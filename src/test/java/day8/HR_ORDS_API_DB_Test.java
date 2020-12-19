package day8;

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
}
