package day9;

import com.github.javafaker.Faker;
import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Repeatable;

public class RepeatedTest {

    @org.junit.jupiter.api.RepeatedTest(5)
    public void test(){
        Faker faker = new Faker();
        System.out.println(faker.chuckNorris().fact());
    }
}
