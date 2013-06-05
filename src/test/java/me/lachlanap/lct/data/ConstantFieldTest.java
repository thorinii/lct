package me.lachlanap.lct.data;

import me.lachlanap.lct.IntConstant;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;

/**
 *
 * @author lachlan
 */
public class ConstantFieldTest {
    /**
     * We need to reset the ConstantTest constants before hand :-)
     */
    @Before
    public void setup(){
        ConstantTest.CONSTANT = 10;
    }

    @Test
    public void testSetField() {
        ConstantField constant = new ConstantField(ConstantTest.class, "CONSTANT", "Constant", 0, 10);
        constant.set(2);

        assertThat(ConstantTest.CONSTANT, is(2));
    }

    public static class ConstantTest {

        @IntConstant(name = "Constant", min = 1, max = 13)
        public static int CONSTANT = 10;
    }
}