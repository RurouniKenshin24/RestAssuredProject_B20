package day9;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.hr.Department;
import testbase.HR_ORDS_TestBase;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LombokTest extends HR_ORDS_TestBase {

    @Test
    public void testLombok(){

        Department d = get("/departments").jsonPath().getObject("items[0]",Department.class);

        int department_id = d.getDepartment_id();
        System.out.println("department_id = " + department_id);
    }

    @DisplayName("GET/departments and save List of POJO")
    @Test
    public void testDepartmentAsPOJO(){
        List<Department> allDepartments = get("/departments").jsonPath().getList("items",Department.class);

        List<Department> temporaryList = new ArrayList<>(allDepartments);
        temporaryList.removeIf(eachDep -> eachDep.getManager_id() == 0);
        temporaryList.forEach(System.out::println);
    }

    @DisplayName("GET/departments and filter results with JsonPath Groovy")
    @Test
    public void testFilterDepartmentsWithGroovy(){
        //it represents each item in the json path!!!
        List<Department> departmentList =
                get("/departments").jsonPath().getList("items.findAll{it.manager_id != null}",Department.class);

        departmentList.forEach(System.out::println);

        List<String> filteredDepartments =
                get("/departments").jsonPath().getList("items.findAll {it.manager_id != null}.department_name");

        filteredDepartments.forEach(System.out::println);

        List<Integer> newList =
                get("/departments").jsonPath().getList("items.department_id.findAll{it > 70}");

        System.out.println(newList);

        List<Integer> newList2 =
                get("/departments").jsonPath().getList("items.department_id.findAll{it > 70 && it < 100}");
        System.out.println(newList2);

        int sumOfDepID =
                get("/departments").jsonPath().getInt("items.department_id.sum()");
        System.out.println(sumOfDepID);

        JsonPath jp = get("/departments").jsonPath();
        //list of department_id from index no 7 to 10
        System.out.println(jp.getList("items.department_id[7..10]"));
        //last index no of department_id list
        System.out.println(jp.getInt("items.department_id[-1]"));
    }
}
