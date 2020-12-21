package day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5_ParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void test1(int number){
        System.out.println(number);

        assertTrue(number < 10);
    }
}
