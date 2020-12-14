package day5;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestCollectionSupport {
    public static void main(String[] args) {
        List<String> listOfNames = Arrays.asList("Rory Hogan","Mariana","Gulbadan","Virginia","Kareem");

        assertThat(listOfNames, hasItems("Virginia","Kareem","Mariana"));

        assertThat(listOfNames, everyItem(containsString("a")));

        //assertThat(listOfNames, everyItem(hasLength(10)));

        //And Logic
        assertThat("Murat Degirmenci", allOf(startsWith("Mu"), containsString("men")));
        //Or Logic
        assertThat("Murat Degirmenci", anyOf(startsWith("Oguz"), containsString("men")));
    }

}
