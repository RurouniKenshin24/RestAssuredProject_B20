package day9;

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
        List<Department> departmentList =
                get("/departments").jsonPath().getList("items.findAll{it.manager_id != null}",Department.class);

        departmentList.forEach(System.out::println);

        List<String> filteredDepartments =
                get("/departments").jsonPath().getList("items.findAll {it.manager_id != null}.department_name");

        filteredDepartments.forEach(System.out::println);

    }
}
