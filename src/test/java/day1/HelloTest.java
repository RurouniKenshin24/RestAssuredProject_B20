package day1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DisplayName on the class level")
public class HelloTest {

    @BeforeAll
    public static void setUp(){
        System.out.println("BeforeAll is running");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("AfterAll is running");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("BeforeEach is running");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("AfterEach is running");
    }

    @DisplayName("DisplayName on the test1 level")
    @Test
    public void test1(){
        Assertions.assertEquals(4,1+3);
    }

    @Disabled
    @DisplayName("DisplayName on the test1 level")
    @Test
    public void test2(){
        assertEquals(12,4*3);
    }
}
