package me.lachlanap.lct;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lachlan
 */
public class SystemTest {

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