package me.lachlanap.lct.data;

import me.lachlanap.lct.Constant;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;

/**
 *
 * @author Lachlan Phillips
 */
public class ConstantFieldTest {

    /**
     * We need to reset the ConstantTest constants beforehand :-)
     */
    @Before
    public void setup() {
        ConstantTest.CONSTANT = 10;
    }

    @Test
    public void testSetField() {
        IntConstantField constant = new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 0, 10);
        constant.set(2);

        assertThat(ConstantTest.CONSTANT, is(2));
    }

    public static class ConstantTest {

        @Constant(name = "Constant", constraints = "1,13")
        public static int CONSTANT = 10;
    }
}