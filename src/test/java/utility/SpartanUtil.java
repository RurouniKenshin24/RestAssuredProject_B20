package utility;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public static Map<String,Object> getRandomSpartanPayload(){
        Map<String,Object> payload = new LinkedHashMap<>();

        Faker faker = new Faker();

        payload.put("name",faker.name().firstName());
        payload.put("gender",faker.demographic().sex());
        payload.put("name",faker.number().numberBetween(5000000000L,9999999999L));

        return payload;
    }
}
