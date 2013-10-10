package me.lachlanap.lct.data;

import me.lachlanap.lct.Constant;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author lachlan
 */
public class ResetConstantTest {

    @Test
    public void resetBoolean() {
        BooleanConstantField field = new BooleanConstantField(ConstantTest.class, "BOOL", "Boolean");
        assertThat(field.get(), is(true));

        field.set(false);
        assertThat(field.get(), is(false));

        field.reset();
        assertThat(field.get(), is(true));
    }

    @Test
    public void resetInt() {
        IntConstantField field = new IntConstantField(ConstantTest.class, "INTEGER", "Integer", 0, 1000);
        assertThat(field.get(), is(234));

        field.set(999);
        assertThat(field.get(), is(999));

        field.reset();
        assertThat(field.get(), is(234));
    }

    public static class ConstantTest {

        @Constant(name = "Boolean")
        public static boolean BOOL = true;
        @Constant(name = "Integer")
        public static int INTEGER = 234;
    }
}
