package me.lachlanap.lct;

import java.io.IOException;
import java.util.Properties;
import me.lachlanap.lct.data.IntConstantField;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import static org.junit.matchers.JUnitMatchers.*;

/**
 *
 * @author Lachlan Phillips
 */
public class LCTManagerTest {

    /**
     * We need to reset the ConstantTest constants before hand :-)
     */
    @Before
    public void setup() {
        ConstantTest.CONSTANT = 10;
    }

    @Test
    public void testRegister() {
        LCTManager lct = new LCTManager();
        lct.register(ConstantTest.class);

        assertThat(lct.getFields().size(), is(1));
        assertEquals(new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13),
                     lct.getFields().get(0));
    }

    @Test
    public void testLoadSettings() {
        LCTManager lct = new LCTManager();
        lct.addConstant(new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13));

        Properties props = new Properties();
        props.setProperty("ConstantTest.Constant", "4");

        lct.loadSettings(props);

        assertThat(ConstantTest.CONSTANT, is(4));
    }

    @Test
    public void testLoadSettingsFailure() {
        LCTManager lct = new LCTManager();
        lct.addConstant(new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13));

        Properties props = new Properties();
        lct.loadSettings(props);

        assertThat(ConstantTest.CONSTANT, is(10));
    }

    @Test
    public void testSaveSettings() throws IOException {
        LCTManager lct = new LCTManager();
        lct.addConstant(new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13));

        ConstantTest.CONSTANT = 5;

        Properties props = new Properties();
        lct.saveSettings(props);

        assertThat(props.getProperty("ConstantTest.Constant"), is("5"));
    }

    @Test
    public void testSet() {
        LCTManager lct = new LCTManager();
        lct.addConstant(new IntConstantField(ConstantTest.class, "CONSTANT", "Constant", 1, 13));

        lct.set("Constant", 11);
        assertThat(ConstantTest.CONSTANT, is(11));
    }

    @Test(expected = ConstantSettingException.class)
    public void testSetFailure() {
        LCTManager lct = new LCTManager();
        lct.addConstant(new IntConstantField(ConstantTest.class, "NONEXISTANT", "NonExistant", 1, 13));

        lct.set("NonExistant", 11);
    }

    public static class ConstantTest {

        @Constant(name = "Constant", constraints = "1,13")
        public static int CONSTANT = 10;
    }
}