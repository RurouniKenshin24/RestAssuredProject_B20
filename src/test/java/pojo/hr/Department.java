package pojo.hr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Data is used for all above like combo annotation!!!
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {

    private int department_id;
    private String department_name;
    private int manager_id, location_id;

//    public Department() {}
//
//    public Department(int department_id, String department_name, int manager_id, int location_id) {
//        this.department_id = department_id;
//        this.department_name = department_name;
//        this.manager_id = manager_id;
//        this.location_id = location_id;
//    }
}
