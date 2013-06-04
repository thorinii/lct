/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.lachlanap.lct;

import me.lachlanap.lct.data.ConstantField;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

/**
 *
 * @author lachlan
 */
public class LCTManagerTest {

    @Test
    public void testRegister() {
        LCTManager lct = new LCTManager();
        lct.register(ConstantTest.class);

        assertThat(lct.getFields().size(), is(1));
        assertThat(lct.getFields(), hasItem(new ConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13)));
    }

    @Test
    public void testLoadSettings() {
    }

    @Test
    public void testSet() {
        LCTManager lct = new LCTManager();
        lct.addField(new ConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13));

        lct.set("Constant", 11);
        assertThat(ConstantTest.CONSTANT, is(11));
    }

    @Test(expected = ConstantSettingException.class)
    public void testSetFailure() {
        LCTManager lct = new LCTManager();
        lct.addField(new ConstantField(ConstantTest.class, "NONEXISTANT", "NonExistant", 1, 13));

        lct.set("NonExistant", 11);
    }

    public static class ConstantTest {

        @IntConstant(name = "Constant", min = 1, max = 13)
        public static int CONSTANT = 10;
    }
}