package utility;

import com.github.javafaker.Faker;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    private static Faker faker = new Faker();

    public static Map<String,Object> getRandomSpartanPayload(){
        Map<String,Object> payload = new LinkedHashMap<>();

        payload.put("name",faker.name().firstName());
        payload.put("gender",faker.demographic().sex());
        payload.put("phone",faker.number().numberBetween(5000000000L,9999999999L));

        return payload;
    }

    public static Spartan getRandomSpartanPOJOPayload(){
        Spartan randomSpartan = new Spartan();

        randomSpartan.setName(faker.name().firstName());
        randomSpartan.setGender(faker.demographic().sex());
        randomSpartan.setPhone(faker.number().numberBetween(5000000000L,9999999999L));

        return randomSpartan;
    }
}
