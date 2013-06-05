package me.lachlanap.lct;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author lachlan
 */
public class SystemTest {
    /**
     * We need to reset the ConstantTest constants before hand :-)
     */
    @Before
    public void setup(){
        ConstantTest.CONSTANT = 10;
    }

    @Test
    public void systemTestInt() {
        LCTManager lct = new LCTManager();
        lct.register(ConstantTest.class);

        lct.set("Constant", 11);
        assertEquals(11, ConstantTest.CONSTANT);
    }

    public static class ConstantTest {

        @IntConstant(name = "Constant", min = 1, max = 13)
        public static int CONSTANT = 10;
    }
}