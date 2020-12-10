package day1;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestIntro {

    @Test
    public void test(){
        assertThat(1+3,is(4));

        assertThat(1+3,equalTo(4));

//        assertThat(1+3,equalTo(8));
        assertThat(1+3,not(8));

    }
}
