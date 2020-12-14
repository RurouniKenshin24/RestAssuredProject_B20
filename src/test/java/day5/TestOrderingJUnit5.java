package day5;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderingJUnit5 {

    @Order(2)
    @Test
    public void testA(){
        System.out.println("A");
    }

    @Order(3)
    @Test
    public void testC(){
        System.out.println("C");
    }

    @Order(1)
    @Test
    public void testD(){
        System.out.println("D");
    }

    @Order(4)
    @Test
    public void testB(){
        System.out.println("B");
    }
}
